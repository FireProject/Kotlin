package com.example.BurningUp

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Friends (val name:String, val statemessage:String)  : AppCompatActivity() {


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
            Log.d("friends.GetFriends","it is start?")
            for (friends in Users.info.friends) {
                Log.d("friends.GetFriends","friend uid : ${friends}")
                friends_num++

                //레퍼런스에서 읽어오면 딕셔너리 형태.

                var test_ref_1 = Firebase.database.getReference("users").child(friends).child("nickName")
                var test_ref_2 = Firebase.database.getReference("users").child(friends).child("stateMessage")


                test_ref_1.get().addOnSuccessListener {
                    Log.d("mmm","success2")
                    obj.nickName = it.getValue().toString()
                }
                test_ref_2.get().addOnSuccessListener{
                    Log.d("mmm","success3")
                    obj.stateMessage = it.getValue().toString()
                }
                friends_constainer.add(obj) //객체를 Info형태의 friends_container에 넣어줌
            }
            ReadFriendsContainSpecificUser()
        }


        fun ReadFriendsContainSpecificUser() {
            var friends_num: Int = 1
            for (element in friends_constainer) {
                friends_num++
            }
        }

    }
}


