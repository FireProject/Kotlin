package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.example.BurningUp.databinding.ActivityNoticeBinding
import com.google.firebase.auth.FirebaseAuth

class NoticeActivity : AppCompatActivity()
{
    private var mBinding: ActivityNoticeBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InitializeTextSize()
        ReturnChatRoom()
    }

    fun InitializeTextSize()
    {
        val DIVIDE_WIDTH = 15
        var text_size = AddChatRoomActivity.device_width / DIVIDE_WIDTH
        binding.etMultiNote.setTextSize(TypedValue.COMPLEX_UNIT_DIP , text_size.toFloat())
        //Log.d("jiwon" , text_size.toString())
    }
    
    //TODO : 지금은 AddChatRoomActivity로 돌아가지만 추후에는 ChatRoomActivity로
    fun ReturnChatRoom()
    {
        binding.btnReturnAddChatRoom.setOnClickListener {
            val intent = Intent(this, AddChatRoomActivity::class.java);
            startActivity(intent)
        }
        binding.btnSuccessReturnAddChatRoom.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "공지 작성을 완료했습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddChatRoomActivity::class.java);
            startActivity(intent)
        }
    }
}