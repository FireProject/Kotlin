package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.example.BurningUp.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity()
{
    private var mBinding: ActivityNoticeBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}