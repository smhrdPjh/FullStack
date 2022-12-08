package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {

    lateinit var adapter : ListAdapter

    //게시물의 uid값이 들어갈 가변배열
    var keyData = ArrayList<String>()
    // bookmark 경로 설정을 위한 선언
    lateinit var bookmarkRef : DatabaseReference

    // bookmark된 게시물의 정보가 들어갈 배열
    var bookmarkList = ArrayList<String>()

    var auth : FirebaseAuth = Firebase.auth








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //realTime에 Database에 필요한 객체선언
        val database = Firebase.database
        //database에 어떤 것을 참조할건지
        // Fragment2에서 전체보기를 눌렀을 때 가져올 데이터 담기는곳(경로)
        val allContent = database.getReference("content")//

        bookmarkRef = database.getReference("bookmarklist")

       // puth() : key 타임스탬프를 찍어줌(고유한값을 만들어줌)(게시물 구분)
//        allContent.push().setValue(
//            ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png","집","https://philosopher-chan.tistory.com/1239")
//        )

        // Fragment2에서 ImageView를 클릭했을 때 넘어와서
        // 구현되어야 할 View들

        //TextView
        // RecyclerView ---> Adapter, data(VO), template(xml)

        //Adapter ---> ListAdapter
        // data(VO) ---> ListVO
        // tamplate ---> layout폴더에 list_tamplate.xml

        //이미지의 id(Int), title(String) ---> VO 로 묶어야할 데이터
        // 3-4개 정도 만들어 놓기

        // template.xml -> imageView하나, TextView, 북마크 ImageView

        //Adapter : 리사이클러뷰를 상속받게 만들기
        // ViewHolder, OncreateView, OnbindingView, getItemCount

        //ListActivity에서 내가만든 ListAdapter를 rv에 적용하기!
        // 단 GridLayoutManager를 사용해서 두줄로 쌓이게 만들자


        val rv = findViewById<RecyclerView>(R.id.rv)
        val tvCategory = findViewById<TextView>(R.id.tvCook)

        // Fragment2에서 intent를 통해 보낸 데이터를 꺼내주기기
        val category = intent.getStringExtra("category")
        tvCategory.text=category

        val data = ArrayList<ListVO>()


        // FireBase에서 데이터를 받아오는 Listner
        val postListener = object : ValueEventListener {
            // 데이터 받아오기가 성공하면 snapshot에 저장시키는 기능
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapshot",snapshot.toString())

                // children key값 =content의 value 값
                Log.d("snapshot2",snapshot.value.toString())
           for (model in snapshot.children){
               val item = model.getValue(ListVO::class.java)
               //model에는 여러가지 게시물이 담겨있고
               // 1개에대한 게시물에 접근하기위해 value를 ListVO타입으로 받기
               if(item != null){
                   data.add(item)
               }

               // uid값 저장
               keyData.add(model.key.toString())



           }
                // 새로고침하는 이유 : 데이터를 받아오는 속도가 adapter가 실행되는 속도보다 느림
                // 그래서 데이터를 받아온 직후에 새로고침 필요함~
                adapter.notifyDataSetChanged()

            }

            //데이터 받아오기가 실패하면 error출력하는 기능
            override fun onCancelled(error: DatabaseError) {

            }

        }
        // 실행시키는 역할
        allContent.addValueEventListener(postListener)

        getBookmarkData()



        //content
        // uid(게시물구분할 수 있는 uid)
        // imgId
        // title
        // url





//        data.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png","집","https://philosopher-chan.tistory.com/1239"))
//        data.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png","스토어","https://philosopher-chan.tistory.com/1237"))
//        data.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png","꿀팁","https://philosopher-chan.tistory.com/1236"))
//        data.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png","지도보기","https://philosopher-chan.tistory.com/1235"))

//        for(i in 0 until data.size){
//            allContent.push().setValue(
//                data[i]
//            )
//        }
         adapter = ListAdapter(this,data,keyData, bookmarkList)

        rv.adapter = adapter

        rv.layoutManager = GridLayoutManager(this,2)

    }

// bookmarklist에 저장되어있는 데이터를 가져오는 함수
 fun getBookmarkData(){
     val postListener2 = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            // bookmarkList를 클리어 하는 이유
            // ListActivity를 들릴때 마다
            // 데이터가 누적됨
            bookmarkList.clear()

            for(model in snapshot.children){
                Log.d("bookmark",model.toString())
                Log.d("bookmark",model.value.toString())
                // 북마크 게시물의 uid값을 bookmarkList에 저장
                bookmarkList.add(model.key.toString())
                Log.d("datasize",bookmarkList.size.toString())
            }
            // adapter가 data 받아오는 것보다 빠르니까 다받아오기 새로고침
            adapter.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {

        }


    }
    // bookmarklist경로에 있는 데이터를 snapshot으로 받아옴
    bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)
 }



}