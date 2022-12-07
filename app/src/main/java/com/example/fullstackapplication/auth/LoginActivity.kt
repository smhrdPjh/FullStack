package com.example.fullstackapplication.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //FirebaseAuth 선언
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences =
            getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        val loginId = sharedPreferences.getString("loginId", "")
        val loginPw = sharedPreferences.getString("loginPw", "")

        // FireabseAuth 초기화
        auth = Firebase.auth
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)
        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)

        etLoginEmail.setText(loginId)
        etLoginPw.setText(loginPw)
        // Login 버튼을 눌렀을 때
        btnLoginLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            auth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 로그인에 성공
                        Toast.makeText(
                            this, "로그인 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                        val editor = sharedPreferences.edit()
                        editor.putString("loginId", email)
                        editor.putString("loginPw", pw)
                        editor.commit()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // 로그인에 실패
                        Toast.makeText(
                            this, "로그인 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            Toast.makeText(applicationContext, "email : $email, pw: $pw", Toast.LENGTH_SHORT).show()
            Log.d("intrologin", "email : $email, pw: $pw")
        }
    }
}