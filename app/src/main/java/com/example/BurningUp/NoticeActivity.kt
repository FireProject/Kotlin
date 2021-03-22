package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_PX
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.example.BurningUp.databinding.ActivityNoticeBinding
import com.google.firebase.auth.FirebaseAuth

class NoticeActivity : AppCompatActivity()
{
    private var mBinding: ActivityNoticeBinding? = null
    private val binding get() = mBinding!!

    companion object
    {
        var device_width : Float = 0f
        var device_height : Float = 0f
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetResolution()
        InitializeTextSize()
        ReturnChatRoom()
    }

    //exp : 해상도를 얻는 Method
    //ref : https://www.androidhuman.com/2016-07-10-kotlin_companion_object
    //TODO :
    // 지금은 아직 사용할 일이 없어서 NoticeActivity에 선언했지만 , 어플이 시작되면
    // 바로 해상도를 구해와야 하기 때문에 가장 첫 페에지인 SplashActivity에 추가해야 할 듯 합니다.
    fun GetResolution()
    {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getRealMetrics(outMetrics)

        var density = resources.displayMetrics.density
        device_height = outMetrics.heightPixels / density
        device_width = outMetrics.widthPixels / density
        //Log.d("jiwon" , device_width.toString() + " " +device_height.toString())
    }

    fun InitializeTextSize()
    {
        val DIVIDE_WIDTH = 15
        var text_size = device_width / DIVIDE_WIDTH
        binding.etMultiNote.setTextSize(TypedValue.COMPLEX_UNIT_DIP , text_size.toFloat())
        //Log.d("jiwon" , text_size.toString())
    }

    //TODO : 지금은 AddChatRoomActivity로 돌아가지만 추후에는 ChatRoomActivity로
    fun ReturnChatRoom()
    {
        binding.btnReturnChatroom.setOnClickListener {
            val intent = Intent(this, AddChatRoomActivity::class.java);
            startActivity(intent)
        }

    }
}