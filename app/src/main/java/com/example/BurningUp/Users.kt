package com.example.BurningUp

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Users {

    companion object {
        data class Info(var friends: ArrayList<String> = arrayListOf(),
                        var nickName: String = "noNamed",
                        var roomId: ArrayList<String> = arrayListOf(),
                        var stateMessage: String = "noState",
                        var profileImg: String ="noURI")
        var info = Info()
        fun readInfo(){
            Log.d("User.readInfo", "start")
            val mAuth = FirebaseAuth.getInstance()
            val mRef = Firebase.database.getReference("users/${mAuth.uid}")

            val childEventListener = object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    // A new comment has been added, add it to the displayed list

                   // dataSnapshot::class.simpleName?.let { Log.d("예진", it) }


                    //이렇게 하세요

                    val commentKey = when(dataSnapshot.key) {
                        "roomIds" -> {
                            info.roomId = dataSnapshot.value as ArrayList<String>
                            Log.d("User.readInfo", "success to get roomdata : ${info.roomId}")
                            Rooms.GetRooms()
                        }
                        "friends" -> {
                            info.friends = dataSnapshot.value as ArrayList<String>
                            Log.d("User.readInfo", "success to get friendsData : ${info.friends}")
                            Friends.GetFriends()
                        }
                        "nickName" -> {
                            info.nickName = dataSnapshot.value as String
                            Log.d("User.readInfo", "success to get nameData : ${info.nickName}")
                            //Log.d("예진", info.nickName)
                        }
                        "stateMessage" -> {
                            info.stateMessage = dataSnapshot.value as String
                            Log.d("User.readInfo", "success to get stateMessage : ${info.stateMessage}")
                        }

                        else ->  {

                        }
                    }



                    // ...
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so displayed the changed comment
                    // ...
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it
                    // ...
                    val info = dataSnapshot.getValue<Info>()
                    val commentKey = dataSnapshot.key
                    if (commentKey == "roomId") {
                        //Rooms.GetRooms()
                    } else if (commentKey == "friends"){
                        //Friends.GetFriends()
                    }
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    // ...
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            }
            mRef.addChildEventListener(childEventListener)

        }
    }
}