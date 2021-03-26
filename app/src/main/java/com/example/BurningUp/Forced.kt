package com.example.BurningUp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
//강제종료시 필요한 클래스
class Forced : Service() {

    private lateinit var auth : FirebaseAuth

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onTaskRemoved(rootIntent: Intent) { //핸들링 하는 부분

        //강제 종료시 자동으로 로그아웃
        auth = FirebaseAuth.getInstance()
        auth.signOut()

        Log.e("Error", "onTaskRemoved - $rootIntent")

        stopSelf() //서비스도 같이 종료
    }
}