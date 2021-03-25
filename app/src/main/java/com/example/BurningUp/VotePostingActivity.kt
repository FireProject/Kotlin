package com.example.BurningUp

import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityVotePostingBinding

class VotePostingActivity : AppCompatActivity()
{
    private var mBinding: ActivityVotePostingBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVotePostingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PrintResolution()
        CheckSmileOrAngry()
    }

    fun PrintResolution()
    {
        Log.d("jiwon" , AddChatRoomActivity.device_height.toString() +"  " + AddChatRoomActivity.device_width.toString());
    }

    fun InitializeTextSize()
    {
        val DIVIDE_WIDTH = 15
        var text_size = AddChatRoomActivity.device_width / DIVIDE_WIDTH
        binding.etVotePostingMemo.setTextSize(TypedValue.COMPLEX_UNIT_DIP , text_size.toFloat())
    }

    fun CheckSmileOrAngry()
    {
        val drawble = resources.getDrawable(R.drawable.fire_mini)
        binding.btnSmile.setOnClickListener {
            Toast.makeText(this.getApplicationContext(),"Smile에 투표했습니다!!!", Toast.LENGTH_SHORT).show()
            binding.btnSmile.setImageDrawable(drawble)
        }

        binding.btnAngry.setOnClickListener {
            Toast.makeText(this.getApplicationContext(),"Angry에 투표했습니다!!!", Toast.LENGTH_SHORT).show()
            binding.btnAngry.setImageDrawable(drawble)
        }
    }
}