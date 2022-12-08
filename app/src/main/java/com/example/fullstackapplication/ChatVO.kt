package com.example.fullstackapplication

data class ChatVO(val name : String , val msg : String) {

    // 반드시! Firebase RealTime DataBase 사용하기 위해서
    // 기본생성자 필요
    constructor() : this("","")

}