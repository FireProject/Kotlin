package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

/* TODO :
<UI>
1. 체크 박스 함수
2. 인원수 조절 -> DB
3. 이미지 추가 기능 + 동그랗게
4. 채팅방 배경색 설정(가장 나중에)  

<FireBase>
1. DB 연결해서 저장해보기
 */

class AddChatRoomActivity : AppCompatActivity()
{
    private lateinit var auth : FirebaseAuth
    private var mBinding: ActivityAddChatRoomBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}