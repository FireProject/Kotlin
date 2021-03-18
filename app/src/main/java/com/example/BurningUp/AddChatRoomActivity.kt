package com.example.BurningUp

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue

/* TODO :
<UI>
1. 
2. 인원수 조절 -> DB
3. 이미지 추가 기능 + 동그랗게
4. 채팅방 배경색 설정(가장 나중에)  

<FireBase>
1. DB 연결해서 저장해보기
 */

class AddChatRoomActivity : AppCompatActivity()
{
    private var mBinding: ActivityAddChatRoomBinding? = null
    private val binding get() = mBinding!!
    public var vote_rate : Int? = null //0 : 매일 , 1 : 일주일 , 2 : 한달

   /* 내 코드
   private lateinit var database : FirebaseDatabase
    private lateinit var ref : DatabaseReference*/

    //대호 코드
   private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {
            val intent = Intent(this, ChangeBackgroundActivity::class.java);
            startActivity(intent)
        }

        /* 내 코드
        database = FirebaseDatabase.getInstance("https://fire-71c1d-default-rtdb.firebaseio.com")
        ref = Firebase.database.reference
        ref.child("Rooms").child("curPerson").setValue((3))*/

        // 대호 코드
        database = Firebase.database.reference
        database.child("Rooms").child("curPerson").setValue(3)

        //TestFirebase()
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

    /*fun TestFirebase()
    {
        Log.d("jiwon" , "call up")

        ref.child("Rooms").child("curPerson").setValue(3)
        var str = ref.child("users").child("nickname").get()
        Log.d("jiwon" , str.toString());
        ref.addValueEventListener(object : ValueEventListener {
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