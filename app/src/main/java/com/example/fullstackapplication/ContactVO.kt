package com.example.fullstackapplication

data class ContactVO(val name : String, val age : Int, val tel : String) {

    // 만약, FireBase의 RealTime Database 용으로
    // 사용되는 VO Class라면, 반드시!! 기본생성자가 정의되어야 한다!!

    // 기본생성자 만들기
    constructor() : this("",0,"")

}