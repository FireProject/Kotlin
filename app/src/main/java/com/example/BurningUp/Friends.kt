package com.example.BurningUp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Friends : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var uid: String? = null
    private lateinit var firebase: FirebaseDatabase
    private lateinit var users_ref: DatabaseReference
    //DB초기화



    companion object{
        data class Info(
            var nickName:String="noname",
            var stateMessage:String="nomessage"
        )

        var info=Info()

        var friends_list= arrayListOf<Info>()

        fun GetFriends()
        {
            for (friends in Users.info.friends)

        }

        //1. 예진 user정보에서  friends리스트를 가져오기
        //2. 가져온 ㅇuser의 데이터를 내 friends_list에서 가져오기


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        uid=auth?.uid
        firebase= FirebaseDatabase.getInstance()
        users_ref=firebase.getReference("users/freinds")   //루트의 자식으로 "users" 연결->DB 테이블 연결


    }


}

