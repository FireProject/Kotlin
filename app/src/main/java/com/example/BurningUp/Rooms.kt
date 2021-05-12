package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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
            //var users: ArrayList<String> = arrayListOf(),
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
                var obj = RoomInfo() //새로운 객체 선언
                var user_contained_rooms_ref : DatabaseReference
                var test : DatabaseReference
                var room_num : Int = 0

                //1. DB에서 데이터 읽어 오기
                //2. 읽어온 데이터 Local 자료구조인 rooms_contain_specific_user 에 넣기

                val tmp : Int = 0;
                for (room_id in Users.info.roomId)
                {
                    Log.d("jiwon" , tmp.toString() + "번째 Test")
                    Log.d("jiwon" , room_num.toString() + " : " + room_id)

                    //1. DB에서 데이터 읽어 오기 : 성공
                    user_contained_rooms_ref = Firebase.database.getReference("rooms").child(room_id)//room_id가 변경되면서
                    test = Firebase.database.getReference("rooms").child(room_id).child("roomName")

                    user_contained_rooms_ref.get().addOnSuccessListener {
                        //Log.d("jiwon" , "${it.value}") //exp : ${문장} : 문장을 string으로 하는 것.
                        Log.d("jiwon", "for문 user_contained rooms_ref : " + it.getValue().toString()) //exp : getValue()를 이용하는 C_Language 형식
                        Log.d("jiwon", "Err_1111")

                        //2. 읽어온 데이터 Local 자료구조인 rooms_contain_specific_user 에 넣기 : 성공
                        // exp : datasnapshot 타입의 자료를 HashMap으로 형변환 하기 위해서 , HashMap을 만들고 , 해당 HashMap을 채운다음 객체에 넣어줍니다.
                        val my_map = HashMap<String,Any> ()
                        for(i in it.getChildren())
                        {
                            my_map[i.key.toString()] = i.value!!
                        }
                        obj.max_person = my_map["MaxPerson"].toString().toInt()
                        obj.cur_person = my_map["curPerson"].toString().toInt()
                        obj.master_uid = my_map["masterUid"].toString()
                        obj.room_color = my_map["roomColor"].toString().toInt()
                        obj.room_name = my_map["roomName"].toString()
                        obj.vote_cycle = my_map["voteCycle"].toString()
                        //obj.room_name = it.getValue().toString();
                        rooms_contain_specific_user.add(obj) //스레드
                        Log.d("jiwon" , rooms_contain_specific_user[0].max_person.toString() + " " + rooms_contain_specific_user[0].room_name + " " + rooms_contain_specific_user[0].cur_person)

                        /*user_contained_rooms_ref.get().addOnSuccessListener{
                        //Log.d("jiwon" , "${it.value}") //exp : ${문장} : 문장을 string으로 하는 것.
                        Log.d("jiwon" , "for문 user_contained rooms_ref : " + it.getValue().toString()) //exp : getValue()를 이용하는 C_Language 형식
                        Log.d("jiwon" , "Err_1")

                        rooms_contain_specific_user.add(obj) //스레드
                         */
                    }

                    //2. 객체를 Container에 추가
                    Log.d("jiwon" , "Err_2222")
                    //Log.d("jiwon", "Local Container 04/08 " + room_num.toString() + "번 방 : " + rooms_contain_specific_user[room_num].cur_person.toString() + rooms_contain_specific_user[room_num].master_uid.toString() + rooms_contain_specific_user[room_num].room_name.toString())
                    room_num++
                }
            }
    }
}