package com.example.BurningUp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class Forced : Service() {

    private lateinit var auth : FirebaseAuth

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onTaskRemoved(rootIntent: Intent) { //핸들링 하는 부분
        auth = FirebaseAuth.getInstance()
        Log.e("Error", "onTaskRemoved - $rootIntent")
        auth.signOut()
        stopSelf() //서비스도 같이 종료
    }
}