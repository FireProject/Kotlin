package com.example.BurningUp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Friends (val name:String, val statemessage:String)  : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private var uid: String? = null
//    private lateinit var firebase: FirebaseDatabase
//    private lateinit var users_ref: DatabaseReference
//    //DB초기화


    companion object {
        data class Info(
                var nickName: String = "noname",
                var stateMessage: String = "nomessage"
        )

        var friends_constainer = arrayListOf<Info>()


        fun GetFriends() {
            var obj = Info()
            var getfriends_ref: DatabaseReference
            var friends_num: Int = 1
            for (friends in Users.info.friends) {
                Log.d("mmm", friends_num.toString() + " : " + friends)
                friends_num++

                //레퍼런스에서 읽어오면 딕셔너리 형태.
                getfriends_ref = Firebase.database.getReference("users/friends").child(friends)
                var test_ref_1 = Firebase.database.getReference("users/friends").child(friends).child("nickName")
                var test_ref_2 = Firebase.database.getReference("users/friends").child(friends).child("stateMessage")


                getfriends_ref.get().addOnSuccessListener {
                    Log.d("mmm", "success1")
                }

                test_ref_1.get().addOnSuccessListener {
                    obj.nickName = it.getValue().toString()
                    Log.d("mmm", "success2")
                }

                test_ref_2.get().addOnSuccessListener {
                    obj.stateMessage = it.getValue().toString()
                    Log.d("mmm", "success3")
                }
                friends_constainer.add(obj) //객체를 Info형태의 friends_container에 넣어줌
            }
            ReadFriendsContainSpecificUser()
        }

        //
        fun ReadFriendsContainSpecificUser() {
            var friends_num: Int = 1
            for (element in friends_constainer) {
                friends_num++
            }
        }

    }
}




//        fun GetFriends()
//        {
//            //Users.info.friend가 배열이고, spilt해서 저장?
//            for (friends in Users.info.friends) {
//                 lateinit var auth: FirebaseAuth
//                 var uid: String? = null
//                 lateinit var firebase: FirebaseDatabase
//                 lateinit var users_ref: DatabaseReference
//
//                //DB초기화
//                auth = FirebaseAuth.getInstance()
//                uid=auth?.uid
//                firebase= FirebaseDatabase.getInstance()
//                users_ref=firebase.getReference("users/freinds")   //루트의 자식으로 "users" 연결->DB 테이블 연결
//
//
//            }
//
//        }

        //1. 예진 user정보에서  friends리스트를 가져오기
        //2. 가져온 ㅇuser의 데이터를 내 friends_list에서 가져오기


//    }

//}

