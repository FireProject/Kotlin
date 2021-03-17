package com.example.BurningUp

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
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
        MakeBaseSeekBar()
        ChangeSeekBar()
        ChangeCheckBox()
    }

    fun MakeBaseSeekBar()
    {
        binding.seekBar.progress = 0 //default
        binding.seekBar.max = 20
    }

    //https://lab.cliel.com/entry/Kotlin-%EC%9C%84%EC%A0%AF-SeekBar
    fun ChangeSeekBar()
    {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
            {
                binding.tvMaxPersonCnt.setText(binding.seekBar.progress.toString() + " 명")
                /*
                var cur_seekbar_value : Int
                cur_seekbar_value = binding.seekBar.progress
                Log.d("test" , cur_seekbar_value.toString())*/
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?)
            {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?)
            {

            }
        })
    }

    fun ChangeCheckBox()
    {
        var vote_rate = 3
        //매일 : 0
        //매주 : 1
        //매월 : 2
        //Except : 3
        binding.cbDay.setOnClickListener {
            if(binding.cbDay.isChecked)
            {
                vote_rate = 0;
                binding.cbWeek.isChecked = false;
                binding.cbMonth.isChecked = false;
            }
            if(binding.cbWeek.isChecked)
            {
                vote_rate = 1;
                binding.cbDay.isChecked = false;
                binding.cbMonth.isChecked = false;
            }
            if(binding.cbMonth.isChecked)
            {
                vote_rate = 2;
                binding.cbDay.isChecked = false;
                binding.cbWeek.isChecked = false;
            }
            Log.d("test" , vote_rate.toString())
        }
    }


}