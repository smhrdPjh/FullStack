package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.fullstackapplication.Fragment.*
import com.example.fullstackapplication.auth.IntroActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //1.
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 어떤 item을 클릭했는지에 따라서
        // FramLayout에 Fragment를 갈아 끼워주자

        //2.
        auth = Firebase.auth
        val fl = findViewById<FrameLayout>(R.id.fl)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)

      imgLogout.setOnClickListener{

          // 접속한 유저의 uid값을 지워준다
          auth.signOut()
          // 로그아웃하고나면 IntroActivity로 이동
          // 이전에 쌓여있는 Activity를 모두 날려주기
          val intent = Intent(this,IntroActivity::class.java)
          intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
          startActivity(intent)

      }
        // auth에 담겨있는 기능
        // createUsersWithEmailandPassword : 회원가입(email, pw)
        // signInWithEmailandPassWord  : 로그인 (email, pw)
        // signInAnonymous : 익명로그인()
        // signOut() : 로그아웃(페이지를 이동하는 기능x , uid값을 null로 초기화시킴)




        bnv.setOnItemSelectedListener {
            item -> //  내가 클릭한 아이템에 대한 정보

            when(item.itemId){
                R.id.tab1 -> {
                    // Fragment1 부분화면으로 fl에 갈아끼워준다
                    // beginTransaction() : fragment 수정 삭제에 도움
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()

                }
                R.id.tab3 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
                R.id.tab5 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment5()
                    ).commit()
                }

            }


           true
        }




    }
}