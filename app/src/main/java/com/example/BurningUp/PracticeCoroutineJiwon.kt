package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class PracticeCoroutineJiwon : AppCompatActivity()
{
    companion object
    {   data class Info
        (
                var max_person : Int = 1,
                var room_notice : String = "",
                var room_name : String = ""
        )

        val rooms_contain_specific_user = arrayListOf<Info>()
        fun Practice()
        {

                var user_contained_rooms_ref: DatabaseReference
                var tmp = Firebase.database.getReference("rooms").child("-MX_KAsCVg4pnYjY5xYB").child("roomName")
                var tmp2 = Firebase.database.getReference("rooms").child("-MXWJyn0-zsp35IIgeLW").child("roomName")
                var obj = Info()

                for(i in (0..3))
                {
                    runBlocking {
                        val job = launch{
                            if (i != 2)
                            {
                                tmp.get().addOnSuccessListener {
                                    //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식

                                    obj.room_name = it.getValue().toString()
                                    Log.d("jiwon", "354 -> " + "1 : " + it.getValue().toString() + " 2 : " + obj.room_name) //exp : getValue()를 이용하는 C_Language 형식
                                    obj.max_person = 15 + i
                                    obj.room_notice = "corou"
                                    Log.d("jiwon", "rooms_contain : " + i.toString() + rooms_contain_specific_user[i].room_notice.toString() + rooms_contain_specific_user[i].max_person.toString() + rooms_contain_specific_user[i].room_name.toString())
                                }
                            }
                            else
                            {
                                tmp2.get().addOnSuccessListener {
                                    //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식

                                    obj.room_name = it.getValue().toString()
                                    Log.d("jiwon", "354 -> " + "1 : " + it.getValue().toString() + " 2 : " + obj.room_name) //exp : getValue()를 이용하는 C_Language 형식
                                    obj.max_person = 15 + i
                                    obj.room_notice = "corou"
                                    Log.d("jiwon", "rooms_contain : " + i.toString() + rooms_contain_specific_user[i].room_notice.toString() + rooms_contain_specific_user[i].max_person.toString() + rooms_contain_specific_user[i].room_name.toString())
                                }
                            }
                            delay(2000)

                        }
                        rooms_contain_specific_user.add(obj)
                        Log.d("jiwon" , "full_initial : " + rooms_contain_specific_user)

                        job.join()
                    }
                }
            }




        /*
        val rooms_contain_specific_user = arrayListOf<Info>()
        fun Test()
        {
            var user_contained_rooms_ref: DatabaseReference
            var tmp = Firebase.database.getReference("rooms").child("-MX_KAsCVg4pnYjY5xYB").child("roomName")
            var tmp2 = Firebase.database.getReference("rooms").child("-MXWJyn0-zsp35IIgeLW").child("roomName")
            var obj = Info()

            for(i in (0..3))
            {
                if (i != 2)
                {
                    tmp.get().addOnSuccessListener {
                        //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식

                        obj.room_name = it.getValue().toString()
                        Log.d("jiwon", "354 -> " + "1 : " + it.getValue().toString() + " 2 : " + obj.room_name) //exp : getValue()를 이용하는 C_Language 형식
                        obj.max_person = 15 + i
                        obj.room_notice = "corou"
                        rooms_contain_specific_user.add(obj)
                        Log.d("jiwon", "rooms_contain : " + i.toString() + rooms_contain_specific_user[i].room_notice.toString() + rooms_contain_specific_user[i].max_person.toString() + rooms_contain_specific_user[i].room_name.toString())
                        }
                }
                else
                {
                    tmp2.get().addOnSuccessListener {
                        //Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 출력 형식

                        obj.room_name = it.getValue().toString()
                        Log.d("jiwon", "354 -> " + "1 : " + it.getValue().toString() + " 2 : " + obj.room_name) //exp : getValue()를 이용하는 C_Language 형식
                        obj.max_person = 15 + i
                        obj.room_notice = "corou"
                        rooms_contain_specific_user.add(obj)
                        Log.d("jiwon", "rooms_contain : " + i.toString() + rooms_contain_specific_user[i].room_notice.toString() + rooms_contain_specific_user[i].max_person.toString() + rooms_contain_specific_user[i].room_name.toString())
                    }
                }
            }
            Log.d("jiwon" , "full_initial : " + rooms_contain_specific_user)
            //hard : 위의로그를 찍어보면 제대로 돌아가지만 결국에는 최종으로 이 함수가 끝나면 데이터가 제대로 들어가지 않음
        }
        fun ReadDataInContainer()
        {
            //Log.d("jiwon" , "why " + rooms_contain_specific_user[0].room_name + rooms_contain_specific_user[1].room_name + rooms_contain_specific_user[2].room_name)
            for(i in (0..3))
            {
                Log.d("jiwon", "rooms_contain : " + i.toString() + rooms_contain_specific_user[i].room_notice.toString() + rooms_contain_specific_user[i].max_person.toString() + rooms_contain_specific_user[i].room_name.toString())
            }
            Log.d("jiwon" , "full : " + rooms_contain_specific_user)
        }*/
    }
}
