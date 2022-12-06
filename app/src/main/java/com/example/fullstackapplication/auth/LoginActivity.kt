package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R
import com.example.fullstackapplication.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //FirebaseAuth 선언
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 초기화화
        auth=Firebase.auth

        val etLoginPw =findViewById<EditText>(R.id.etLoginPw)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)


        // Login 버튼을 눌렀을 떄
        btnLoginLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            // 로그인 할때 사용하는 메서드
            auth.signInWithEmailAndPassword(email,pw)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        //로그인에 성공
                        Toast.makeText(this,"로그인성공",Toast.LENGTH_SHORT).show()
                  val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        // 로그인에 실패
                        Toast.makeText(this,"로그인실패",Toast.LENGTH_SHORT).show()
                    }
                }

            Toast.makeText(this@LoginActivity,"$email, $pw", Toast.LENGTH_SHORT).show()




    }
}
}