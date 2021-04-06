package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

class Rooms : AppCompatActivity()
{
    companion object
    {
        data class Info
            (
            var cur_person : Int = 1,
            var master_uid : String = "",
            var max_person : Int = 20,
            var room_name : String = "",
            var room_notice : String = ""
            //var users : HashMap<Any,Any>
            )

            //Function
            var my_rooms_list = arrayListOf<Info>()

            fun GetRooms()
            {
                /*
                // hard : reference자체를 여기서 만들고 다른 클래스에서는 호출만해서 사용
                // 0. DB Reference도 다 여기서 함.
                // 1. 예진이 users의 정보에서 내 user가 들어간 방만 가져오기
                // 2. 가져온 users 데이터를 로컬의 my_rooms_list에 추가
                */

                //current_room
                for (room_id in Users.info.roomId)
                {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

    }
}