package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListAdapter(val context: Context, val list : ArrayList<ListVO>, var keyData : ArrayList<String>, var bookmarkList : ArrayList<String> )  : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val database =Firebase.database
    val auth : FirebaseAuth = Firebase.auth
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvCook : TextView
        val imgFish : ImageView
        val imgBookMark : ImageView


        init {
            tvCook = itemView.findViewById(R.id.tvCook)
            imgFish = itemView.findViewById(R.id.imgFish)

            imgBookMark = itemView.findViewById(R.id.imgMark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.tip_list, null)



        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvCook.setText(list.get(position).tvCook)
        //holder.imgFish.setImageResource(list.get(position).imgFish)

         Glide.with(context).load(list[position].imgFish)
            .into(holder.imgFish)

            holder.imgFish.setOnClickListener {
                // 버튼을 클릭했을 때 일어나야하는 일!!!
                // SharedPreferences에 Url값 저장하기
                val url =list[position].url
                // 1. SharedPreference 가져오기
                // 1) 이름지정
                // 2) 모드 설정
                // requireActivity.getSharedPreference()
                val spf = context.getSharedPreferences(
                    "mySPF",
                    Context.MODE_PRIVATE
                )
                // 2. SharedPreference에 데이터를 저장할 수있는 editor가져오기

                val editor= spf.edit()
                // 3. editor를 통해서 데이터 저장하기
                editor.putString("url", url).commit()
                // +Fragment1에서 꺼내서 url에 설정

                val intent = Intent(context, WebViewActivity::class.java)
               // intent.putExtra("url",list[position].url)
                context.startActivity(intent)


            }

        //클릭했을 때 색깔을 바꾸면 기존에 있던 북마크는 색이 안칠해져있음
        // adapter가 실행이 되는 순간 북마크로 있던 데이터들은 바로 색칠 될 수 있게
        if(bookmarkList.contains(keyData[position])){

            holder.imgBookMark.setImageResource(R.drawable.bookmark_color)
        }else{
            holder.imgBookMark.setImageResource(R.drawable.bookmark_white)


        }



        holder.imgBookMark.setOnClickListener{

            //Firebase에 있는 bookmarklist로 접근

            val bookmarkRef = database.getReference("bookmarklist")
           // 누가 북마크를 눌렀는지 + 북마크 키값 Firebase에저장하기
            bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")

        // 이미 저장이 되어있는 게시물인지 아닌지
        // bookmarkList에 해당 게시물이 포함되어있는지
            if(bookmarkList.contains(keyData[position])){
                //북마크가 리스트에포함 되어있다면 취소
                // database에서 해당 keyData를 삭제
                //imgbookmark를 하얗게 만들자

                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()

               // holder.imgBookMark.setImageResource(R.drawable.bookmark_white)

            }else{
                //북마크가 리스트에 포함 되어있지 않다면 추가
                // database에 해당 keyData를 추가
                // imgbookmark를 까맣게 만들자
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")

                //holder.imgBookMark.setImageResource(R.drawable.bookmark_color)


            }



        }

        // 북마크 모양의 이미지를 클릭했을 때
        // 해당 게시물의 uid값이 bookmarklist경로로 들어가야함





        }



    override fun getItemCount(): Int {

        return  list.size
    }
}