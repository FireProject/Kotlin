package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding.inflate
import com.example.BurningUp.databinding.ActivityUploadPostBinding

class UploadPostActivity : AppCompatActivity()
{
    private var mBinding: ActivityUploadPostBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUploadPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ReturnUploadPostActivity()
    }

    fun ReturnUploadPostActivity()
    {
        binding.btnUploadSuccess.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "게시물을 등록했습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, VoteActivity::class.java);
            startActivity(intent)
        }
    }
}