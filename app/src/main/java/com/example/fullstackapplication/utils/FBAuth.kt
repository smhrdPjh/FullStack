package com.example.fullstackapplication.utils

import android.icu.text.SimpleDateFormat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.util.*

class FBAuth {

    companion object{
        lateinit var auth : FirebaseAuth

        // return 타입을 String으로 지정해놔서 .toString 안쓰고 리턴가능
        fun getUid() : String{
            auth = FirebaseAuth.getInstance()
            return  auth.currentUser!!.uid
        }
        // 현재시간을 가져오는 함수
        fun getTime() : String {
            // Calendar 객체는 getInstance() 메소드로 객체를 생성하게 되어있음
            val currentTime = Calendar.getInstance().time
            // 시간을 나타낼 형식, 어느위치의 시간을 가져올건지 설정
            // "yyyy.MM.dd HH:mm:ss"
            val time = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentTime)

         return time
        }
    }



}