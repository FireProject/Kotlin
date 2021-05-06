package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BurningUp.databinding.ActivityChangePasswordBinding
import com.example.BurningUp.databinding.ActivityFindFriendsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FindFriendsActivity : AppCompatActivity() {

    private var mBinding: ActivityFindFriendsBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth: FirebaseAuth
    private var uid: String? = null
    private lateinit var firebase: FirebaseDatabase
    private lateinit var users_ref: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {

        mBinding= ActivityFindFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        // 돋보기 버튼이 클릭되었을 때
        binding.searchBtn.setOnClickListener {
            if(binding.findFriends.text.isNotEmpty())   //친구 찾기 목록에 뭔가 입력되어있으면
            {
                //.text로 접근해서 받아들여진거 읽고 16진수로 변환 후 친구찾ㄷ기
            }

        }



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)
    }
}