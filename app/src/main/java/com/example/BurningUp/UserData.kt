package com.example.BurningUp

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserData(val name:String, val statemessage:String) : AppCompatActivity() {
    companion object{
        data class Info (var nickName:String="noname", var stateMessage:String="nomessage")

    var usr_Info=Info()


        fun GetUsr() {
            var obj =Info()
            var u_ref: DatabaseReference


                //레퍼런스에서 읽어오면 딕셔너리 형태.
                 u_ref= Firebase.database.getReference("users")
                var test_ref_1 = Firebase.database.getReference("users").child("nickName")
                var test_ref_2 = Firebase.database.getReference("users").child("stateMessage")

                u_ref.get().addOnSuccessListener {
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
               usr_Info=obj
            }

        }




    }


