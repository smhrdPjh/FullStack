package com.example.fullstackapplication.auth

import android.R.bool
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // FirebaseAuth 선언
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)
        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinCheck = findViewById<EditText>(R.id.etJoinCheck)

        // auth를 초기화
        auth =Firebase.auth
        // Firebase.auth : 로그인, 회원가입, 인증 시스템에대한 모든 기능이 담겨있다!

        //btnJoinJoin 눌렀을 때
        btnJoinJoin.setOnClickListener {
            var isjoin = true
            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val checkPw = etJoinCheck.text.toString()

            //EditText에 내용이 있는지
            if(email.isEmpty()){
                isjoin=false
                Toast.makeText(this,"이메일을 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            if(pw.isEmpty()){
                isjoin = false
                Toast.makeText(this,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
            if(checkPw.isEmpty()){
                isjoin = false
                Toast.makeText(this,"비밀번호 재입력을 입력해주세요.",Toast.LENGTH_SHORT).show()

            }
            // 비밀번호랑 재입력한 비밀번호가 똑같은지
            if(pw !=checkPw){
                isjoin = false
                Toast.makeText(this,"비밀번호및 비밀번호재입력을 똑같이 입력해주세요.",Toast.LENGTH_SHORT).show()
            }

            // 비밀번호가 8자리 이상인지
            if(pw.length<8){
                isjoin = false
                Toast.makeText(this,"비밀번호를 8자리 이상 입력해주세요.",Toast.LENGTH_SHORT).show()
            }



            if (isjoin==true){
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this) { task ->
                        // task ---> 보낸 후 결과 (성공했는지 실패했는지)
                        if (task.isSuccessful) {
                            // 성공시 실행시킬 코드
                            Toast.makeText(this,"회원가입성공",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()


                        } else {
                            // 실패시 실행시킬 코드
                            Toast.makeText(this,"실패",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
          //  Toast.makeText(this@JoinActivity,"$email, $pw",Toast.LENGTH_SHORT).show()

            //create가 보내고 있는 전달인자 2개(email, pw)는 실제로 회원가입 정보 전달(Firebase로)


        }



    }
}