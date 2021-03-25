package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.google.firebase.database.FirebaseDatabase

//exp : 당분간(3월)은 박지원의 MainActivity
class AddChatRoomActivity : AppCompatActivity()
{
    private var mBinding: ActivityAddChatRoomBinding? = null
    private val binding get() = mBinding!!
    public var vote_rate : Int? = null //0 : 매일 , 1 : 일주일 , 2 : 한달

    //exp : 어플 전체에서 사용할 static 변수
    companion object
    {
        var device_width : Float = 0f
        var device_height : Float = 0f
    }

    //TODO : DB 로그인을 하면 연결될 것으로 기대
    // DB 시행착오
    //private lateinit var database : FirebaseDatabase
    //private lateinit var my_ref : DatabaseReference
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DB method
        //database = FirebaseDatabase.getInstance().getReference()
        //database.child("Rooms").child("curPerson").setValue(3)
        //TestFirebase()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")
        Log.d("jiwon" , "success");
        /*val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Rooms")

        myRef.child("test").setValue("Hello, World!")*/

        //Move other Page Method
        OpenDialog()
        OpenNotice()
        OpenVotePosting()

        //Layout Method
        MakeBaseSeekBar()
        ChangeSeekBar()
        ChangeRadioBox()

        //Other Method
        GetResolution()
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
    fun OpenDialog()
    {
        binding.btnDialog.setOnClickListener {
            val dialog = CustomDialog(this) //hard : dialog가 CustomDialog의 객체이며 생성자를 추가하여 호출.
            dialog.myDig()
        }
    }

    fun OpenNotice()
    {
        binding.btnNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java);
            startActivity(intent)
        }
    }

    fun OpenVotePosting()
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
        //Log.d("jiwon" , device_width.toString() + " " +device_height.toString())
    }

    /*
    fun TestFirebase()
    {
        Log.d("jiwon" , "call up")

        my_ref.child("Rooms").child("curPerson").setValue(3)
        var str = my_ref.child("users").child("nickname").get()
        Log.d("jiwon" , str.toString());
        my_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d("jiwon",
                        "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError)
            {
                Log.d("jiwon",
                        "Failed to read value.", error.toException())

            }
        })
    }*/
}