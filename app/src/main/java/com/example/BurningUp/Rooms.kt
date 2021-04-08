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
import java.util.HashMap

class Rooms : AppCompatActivity()
{
    companion object
    {
        data class Info
            (
            var max_person : Int = 1,
            var room_notice : String = "",
            var master_uid : String = "",
            var cur_person : Int = 20,
            var room_color : Int = 0,
            var room_name : String = "",
            var users: ArrayList<String> = arrayListOf(),
            var vote_cycle : String = ""
            )

            //Function
            var rooms_contain_specific_user = arrayListOf<Info>()

            /*
                exp : Users.readInfo() + GetRooms() 설명
                  1. Users.readInfo()에서 DB의 데이터를 읽어오고 Datasnapshot까지 String으로 Parsing을 해주기 때문에 호출만 하면 DB는 해결
                  2. 가져오는 String이 ArrayList<string>이므로 끊어서 For문을 돌려줍니다.
                  3. For문을 돌려서 나의 Local Container에 저장하면 메모리 상에서 빠르게 가져올 수 있습니다.
                  4. 소속된 Room을 읽어오기 때문에 그 후에는 DB에 접근합니다.
            */
            fun PushLocalContainer()
            {
                var obj = Info() //hard : obj를 비우는 method X, 그러므로 그냥 새로운 객체 선언
                var user_contained_rooms_ref : DatabaseReference
                var room_num : Int = 0
                for (room_id in Users.info.roomId)
                {
                    Log.d("jiwon" , room_num.toString() + " : " + room_id)


                    //TODO : user_contained_rooms_ref에서 읽어오면 Dictionary형식인데 이거 한번에 하는 게있을텐데 지금을 모름.
                    user_contained_rooms_ref = Firebase.database.getReference("rooms").child(room_id)//room_id가 변경되면서
                    var test_ref_1 = Firebase.database.getReference("rooms").child(room_id).child("roomName")
                    var test_ref_2 = Firebase.database.getReference("rooms").child(room_id).child("MaxPerson")

                    //hard : 한 방에 할 수 있는 구문
                    user_contained_rooms_ref.get().addOnSuccessListener{
                        //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식
                        Log.d("jiwon" , "for문 user_contained rooms_ref : " + it.getValue().toString()) //exp : getValue()를 이용하는 C_Language 형식

                        //1. 읽어온 데이터(얘는 dictionary)에서 값을 추출해서 객체를 초기화
                        //2. 객체를 넣음 -> dictionary를 Info type으로 변화시키면 여기서 한번에 되고 test_ref가 필요 없음
                    }
                    test_ref_1.get().addOnSuccessListener{
                        //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식
                        Log.d("jiwon" , "for문 Test : " + it.getValue().toString()) //exp : getValue()를 이용하는 C_Language 형식

                        //1. 읽어온 데이터에서 값을 추출해서 객체를 초기화
                        obj.room_name = it.getValue().toString()
                    }
                    test_ref_2.get().addOnSuccessListener{
                        Log.d("jiwon" , "for문 Test : " + it.getValue().toString()) //exp : getValue()를 이용하는 C_Language 형식

                        //1. 읽어온 데이터에서 값을 추출해서 객체를 초기화
                        obj.max_person = it.getValue().toString().toInt()
                    }
                    
                    //2. 객체를 Container에 추가
                    rooms_contain_specific_user.add(obj)
                    Log.d("jiwon", "Local Container 04/08 " + room_num.toString() + "번 방 : " + rooms_contain_specific_user[room_num].cur_person.toString() + rooms_contain_specific_user[room_num].master_uid.toString() + rooms_contain_specific_user[room_num].room_name.toString())
                    room_num++
                }
                //TODO : 세마포어를 이용해서 아래의 코드가 for문 내부의 비동기 firebase를 완료하고 호출되어야 합니다.
                //hard : 연습해보니까 함수 호출은 메인스레드여서 걍 빨리 해버리나

                //ReadRoomsContainSpecificUser()
            }

            //ref : https://www.javaer101.com/ko/article/45145109.html
            fun ReadRoomsContainSpecificUser() //call later than above for -> use semaphore
            {
                var room_num: Int = 1
                for (element in rooms_contain_specific_user) {
                    Log.d("jiwon", "Local Container " + room_num.toString() + "번 방 : $element}")
                    room_num++
                }
            }
    }
}