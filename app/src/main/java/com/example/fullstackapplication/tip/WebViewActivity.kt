package com.example.fullstackapplication.tip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        //받아온 Url값을 내서
        // 해당 웹페이지가 WebView에 뜨게 만들자
        // Fragment 1202
        val wv = findViewById<WebView>(R.id.wv)
        //// val address = intent.getStringExtra("url")


        val spf = this.getSharedPreferences(
            "mySPF",
            Context.MODE_PRIVATE
        )
//        WebView에 원하는 웹페이지 띄우기
//        1. 주소 지정
        val url = spf.getString("url","")

//        2. 설정 변경 (JavaScript기본적으로 지원안됨 그래서 사용 가능하도록 '허용')
        val ws = wv.settings
        ws.javaScriptEnabled = true

//        3. WebView 에 Client 설정
        wv.webViewClient = WebViewClient()
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        4. WebView 에 1.주소 적용
        // default Value가 있음
        wv.loadUrl(url!!)

       //// wv.loadUrl(address!!)

    }
}