package com.example.BurningUp

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Rooms : AppCompatActivity()
{
    companion object
    {
        data class RoomInfo
            (
            var max_person : Int = 18,
            var room_notice : String = "",
            var master_uid : String = "",
            var cur_person : Int = 3,
            var room_color : Int = 0,
            var room_name : String = "",
            var users: ArrayList<String> = arrayListOf(),
            var vote_cycle : String = ""
            )

            var rooms_contain_specific_user = arrayListOf<RoomInfo>()

            /*
                exp : Users.readInfo() + GetRooms() 설명
                  1. Users.readInfo()에서 DB의 데이터를 읽어오고 Datasnapshot까지 String으로 Parsing을 해주기 때문에 호출만 하면 DB는 해결
                  2. 가져오는 String이 ArrayList<string>이므로 끊어서 For문을 돌려줍니다.
                  3. For문을 돌려서 나의 Local Container에 저장하면 메모리 상에서 빠르게 가져올 수 있습니다.
                  4. 세마포어와 상관없이 DB읽는 듯. 유저가 속한 Room이 없어서 데이터가 나오지 않았던 것.
            */

            fun GetRooms()
            {
                 //새로운 객체 선언
                var user_contained_rooms_ref : DatabaseReference
                var test : DatabaseReference
                var room_num : Int = 0

                //1. DB에서 데이터 읽어 오기
                //2. 읽어온 데이터 Local 자료구조인 rooms_contain_specific_user 에 넣기

                val tmp : Int = 0;
                for (room_id in Users.info.roomId)
                {
                    var obj = RoomInfo()
                    Log.d("roomGetrooms" , "${tmp}번째 roomID : ${room_id}")

                    //1. DB에서 데이터 읽어 오기 : 성공

                    user_contained_rooms_ref = Firebase.database.getReference("rooms").child(room_id)//room_id가 변경되면서

                    user_contained_rooms_ref.get().addOnSuccessListener {

                        it.children.forEach {
                            Log.d("roomGetrooms","${it.getValue()}")
                        }


                        Log.d("roomGetrooms" , "MaxPerson : ${obj.max_person}")
                        Log.d("roomGetrooms" , "curPerson : ${obj.cur_person}")
                        Log.d("roomGetrooms" , "masteruid : ${obj.master_uid}")
                        Log.d("roomGetrooms" , "roomColor : ${obj.room_color}")
                        Log.d("roomGetrooms" , "roomName : ${obj.room_name}")
                        Log.d("roomGetrooms" , "voteCycle : ${obj.vote_cycle}")

                    }
                    room_num++
                }
            }
    }
}