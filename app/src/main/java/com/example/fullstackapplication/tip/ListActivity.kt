package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

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

        val list = ArrayList<ListVO>()


        list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png","집","https://philosopher-chan.tistory.com/1239"))
        list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png","스토어","https://philosopher-chan.tistory.com/1237"))
        list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png","꿀팁","https://philosopher-chan.tistory.com/1236"))
        list.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png","지도보기","https://philosopher-chan.tistory.com/1235"))

        val adapter = ListAdapter(this,list)

        rv.adapter = adapter

        rv.layoutManager = GridLayoutManager(this,2)





    }
}