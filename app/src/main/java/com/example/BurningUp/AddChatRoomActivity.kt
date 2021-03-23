package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.google.firebase.database.FirebaseDatabase


/* TODO :
<UI>
1. 이미지 추가 기능 + 동그랗게

<FireBase>
1. DB 연결해서 저장해보기
 */

class AddChatRoomActivity : AppCompatActivity()
{
    private var mBinding: ActivityAddChatRoomBinding? = null
    private val binding get() = mBinding!!
    public var vote_rate : Int? = null //0 : 매일 , 1 : 일주일 , 2 : 한달

    // 내 코드
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

        //Layout Method
        MakeBaseSeekBar()
        ChangeSeekBar()
        ChangeRadioBox()
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