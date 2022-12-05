package com.example.fullstackapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLoginPw =findViewById<EditText>(R.id.etLoginPw)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)


        // Login 버튼을 눌렀을 떄
        btnLoginLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            Toast.makeText(this@LoginActivity,"$email, $pw", Toast.LENGTH_SHORT).show()
        }


    }
}