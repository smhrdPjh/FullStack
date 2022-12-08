package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

class BookmarkAdapter(val context: Context, val data : ArrayList<ListVO>, val keyData : ArrayList<String>, val bookmarkList : ArrayList<String>) :RecyclerView.Adapter<BookmarkAdapter.ViewHoler>() {

    inner class ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imgMark : ImageView
        val imgContent : ImageView
        val tvTitle : TextView


        init {
            imgMark = itemView.findViewById(R.id.imgMark)
            imgContent = itemView.findViewById(R.id.imgFish)
            tvTitle = itemView.findViewById(R.id.tvCook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        // list_template.xml을 눈에 보이는 View객체로 바꾼다
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.tip_list, null)



        return ViewHoler(view)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        // 북마크데이터에 포함되어있는지를 판단해서
        // view + data랑 합쳐주는 작업을 진행
        if(bookmarkList.contains(keyData[position])){
            holder.tvTitle.text = data[position].tvCook
            Glide.with(context).load(data[position].imgFish)
                .into(holder.imgContent)

            // Glide : 웹에 있는 이미지를 가져와서 세팅
            // imgFish ---> 이미지 주소값
        }
        if(bookmarkList.contains(keyData[position])){
            holder.imgMark.setImageResource(R.drawable.bookmark_color)
        }
    }

    override fun getItemCount(): Int {
       return  data.size
    }
}