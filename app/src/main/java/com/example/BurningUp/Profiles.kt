package com.example.BurningUp

import android.util.Log


//프로필은 int로 받는데 이거 uid 되면 될듯?
class Profiles(val profile: String?=null,val name: String?=null, val state: String? =null ) //레벨 추가해주기-> 불 크기, 프로필 사진도 좀 이따가
{
    val TAG:String ="로그"
    init{
        Log.d(TAG,"Profiles -init() called ")
    }
}

