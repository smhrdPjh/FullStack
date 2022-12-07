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

class ListAdapter(val context: Context, val list : ArrayList<ListVO> )  : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvCook : TextView
        val imgFish : ImageView

        init {
            tvCook = itemView.findViewById(R.id.tvCook)
            imgFish = itemView.findViewById(R.id.imgFish)

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




        }



    override fun getItemCount(): Int {

        return  list.size
    }
}