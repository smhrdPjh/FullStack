package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)

        auth = Firebase.auth
        // no -> firevase에서 익명으로 접속할 수 있는 권한 받아와서 성공하면 MainActivity로 이동

        btnIntroJoin.setOnClickListener {
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }

        btnIntroLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


        btnIntroNo.setOnClickListener {
            auth.signInAnonymously()
                .addOnCompleteListener(this){
                    task->
                    if(task.isSuccessful){
                        //익명로그인 성공
                        Toast.makeText(this,"비회원로그인 성공",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        //익명로그인 실패 (실패면 거의 서버 문제)
                    }
                }

        }


    }
}