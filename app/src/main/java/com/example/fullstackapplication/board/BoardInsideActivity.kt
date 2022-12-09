package com.example.fullstackapplication.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    lateinit var imgIn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        // id 값
        val tvInTitle = findViewById<TextView>(R.id.tvInTitle)
        val tvInTime = findViewById<TextView>(R.id.tvInTime)
        val tvInContent = findViewById<TextView>(R.id.tvInContent)
        imgIn = findViewById<ImageView>(R.id.imgIn)

        val content = intent.getStringExtra("content")
        val time = intent.getStringExtra("time")
        val title = intent.getStringExtra("title")

        // 이미지를 Firebase 에서 꺼내올 때 사용
        val key = intent.getStringExtra("key")

        tvInTitle.text = title.toString()
        tvInTime.text = time.toString()
        tvInContent.text = content.toString()

        // 게시물의 uid 값으로 Activity 저장

        getImageData(key.toString())
    }

    // img 저장 경로가 외부인지 내부인지에 따라 사용할 코드가 다르다

    /**firebase 에서 img data 가져오기*/
    fun getImageData(key:String){
        val storageReference = Firebase.storage.reference.child("$key.png")

        storageReference.downloadUrl.addOnCompleteListener { task->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imgIn) // 지역변수
            }
        }
    }

}