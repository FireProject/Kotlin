package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BurningUp.databinding.ActivityChangeProfileBinding
import com.example.BurningUp.databinding.ActivityVoteBinding

class VoteActivity : AppCompatActivity()
{
    private var mBinding: ActivityVoteBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun MakeGridLayout()
    {
        //TODO : 이미지의 height는 비율로 가져오자
    }
}