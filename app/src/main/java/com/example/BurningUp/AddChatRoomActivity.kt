package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

//exp : 당분간(3월)은 박지원의 MainActivity
class AddChatRoomActivity : AppCompatActivity()
{
    private var mBinding: ActivityAddChatRoomBinding? = null
    private val binding get() = mBinding!!

    //exp : 어플 전체에서 사용할 static 변수
    companion object
    {
        var device_width : Float = 0f
        var device_height : Float = 0f
    }

    private var vote_rate : Int? = null //0 : 매일 , 1 : 일주일 , 2 : 한달
    private var max_person : Int = 20
    //exp : DB
    private lateinit var auth : FirebaseAuth
    private var uid : String? = null
    private lateinit var firebase : FirebaseDatabase //Web DB 객체변수
    private lateinit var rooms_ref : DatabaseReference //Web Db에서 원하는 key(이건 room) 접근하기 위한 변수

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //exp : DB 초기화
        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance() //web DB를 .json 파일을 참고해서 연결
        rooms_ref = firebase.getReference("rooms") //root의 자식으로 "rooms" 연결

        //Move other Page Method
        MoveDialogActivity()
        MoveNoticeActivity()
        MoveVoteActivity()
        MoveVotePostingActivity()

        //Layout Method
        MakeBaseSeekBar()
        ChangeSeekBar()
        ChangeRadioBox()

        //Other Method
        GetResolution()
        Finally_Make_Chatroom()
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
                max_person = binding.seekBar.progress
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

    fun ChangeRadioBox()
    {
        binding.radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, checked_id: Int)
            {
                if(checked_id == binding.rbDay.id)
                {
                    vote_rate = 0;
                }
                else if(checked_id == binding.rbWeek.id)
                {
                    vote_rate = 1;
                }
                else
                {
                    vote_rate = 2;
                }
                Log.d("jiwon" , vote_rate.toString());
            }
        })
    }

    //exp : button을 누르면 내부에서 CustomDialog 자체를 호출해 버림.
    fun MoveDialogActivity()
    {
        binding.btnDialog.setOnClickListener {
            val dialog = CustomDialog(this) //hard : dialog가 CustomDialog의 객체이며 생성자를 추가하여 호출.
            dialog.myDig()
        }
    }

    fun MoveNoticeActivity()
    {
        binding.btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java);
            startActivity(intent)
        }
    }

    fun MoveVoteActivity()
    {
        binding.btnVote.setOnClickListener {
            val intent = Intent(this, VoteActivity::class.java);
            startActivity(intent)
        }
    }
    fun MoveVotePostingActivity()
    {
        binding.btnVotePosting.setOnClickListener {
            val intent = Intent(this, VotePostingActivity::class.java);
            startActivity(intent)
        }
    }


    //exp : 해상도를 얻는 Method
    //ref : https://www.androidhuman.com/2016-07-10-kotlin_companion_object
    //TODO : 지금은 아직 사용할 일이 없어서 Addchatroom에 선언했지만 , 어플이 시작되면
    //TODO : 바로 해상도를 구해와야 하기 때문에 가장 첫 페에지인 SplashActivity에 추가해야 할 듯 합니다.
    fun GetResolution()
    {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getRealMetrics(outMetrics)

        var density = resources.displayMetrics.density
        device_height = outMetrics.heightPixels / density
        device_width = outMetrics.widthPixels / density
    }

    //exp : 지금까지 작성한 모든 정보를 포함해서 새로운 chat_room을 제작
    fun Finally_Make_Chatroom()
    {
        binding.btnMake.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "변경사항이 저장 되었습니다" , Toast.LENGTH_SHORT).show()

            val map_of_rooms_members = HashMap<Any, Any>()
            map_of_rooms_members["curPerson"] = 1
            map_of_rooms_members["masterUid"] = uid.toString()
            map_of_rooms_members["MaxPerson"] = max_person.toInt()
            map_of_rooms_members["roomName"] = binding.etChatname.text.toString()
            map_of_rooms_members["roomNotice"] = "No Text"
            map_of_rooms_members["roomBackgroud"] = "Red"
            rooms_ref.push().setValue(map_of_rooms_members)
        }
    }
}