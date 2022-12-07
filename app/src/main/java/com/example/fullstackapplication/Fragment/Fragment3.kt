package com.example.fullstackapplication.Fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class Fragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //btnWrite를 클릭하면 BoardWriteActivity로 이동


        var view = inflater.inflate(R.layout.fragment_3, container, false)


        return view
    }

}