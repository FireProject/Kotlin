package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityVoteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.frame_layout.view.*

class VoteActivity : AppCompatActivity()
{
    private var mBinding: ActivityVoteBinding? = null
    private val binding get() = mBinding!!
    private lateinit var auth : FirebaseAuth
    private var uid : String? = null
    private lateinit var firebase : FirebaseDatabase
    private lateinit var count_data_ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance()
        count_data_ref = firebase.getReference("countData")

        MoveUploadPostActivity()
        ReturnAddChatRoomAcitivity()

        //exp : 게시물 + DB 관리 method
        MakeCurrentPosting()
        AddNewPosting()
    }

    fun MoveUploadPostActivity()
    {
        binding.btnUploadPost.setOnClickListener {
            val intent = Intent(this, UploadPostActivity::class.java);
            startActivity(intent)
        }
    }

    fun ReturnAddChatRoomAcitivity()
    {
        binding.btnReturnAddChatRoom.setOnClickListener {
            val intent = Intent(this, AddChatRoomActivity::class.java);
            startActivity(intent)
        }
    }

    //exp : DB에서 현재 올라온 인증 게시물의 개수를 받아옵니다.
    fun GetPostingCountFromFirebase() : Int
    {
        var cur_posting_cnt : Int = 0
        count_data_ref.child("curPostCount").get().addOnSuccessListener {
            cur_posting_cnt = it.getValue().toString().toInt()
            Log.d("jiwon" , "1 " + cur_posting_cnt.toString())
        }
        Log.d("jiwon" , "2 " + cur_posting_cnt.toString())
        return cur_posting_cnt

        //TODO : 현재 log_2가 log_1보다 먼저 나오는 걸로 보아 DB에서 값을 받아오기 전에 초기화한 0이 그냥 리턴되버림.
    }

    //exp : DB에서 받아온 인증 게시물의 개수만큼 게시물을 GridLayout에 표현
    //TODO : 지금은 버튼 호출이지만 추후에는 들어오자마자 call-back
    fun MakeCurrentPosting()
    {
        val cur_posting_cnt = GetPostingCountFromFirebase()

        //ref : https://www.javaer101.com/article/6583845.html
        binding.btnAddPosting.setOnClickListener {
            //for (i in (0..cur_posting_cnt))// TODO : 세마포어(비동기) 한 후에 해결
            for(i in (1..12)) //hard : 1 ~ 12가 12개임
            {
                val frame = ScrollView(this) // create frame
                layoutInflater.inflate(R.layout.frame_layout, frame) // add layout to frame
                frame.tag = i.toString() //add tag to enable finding specific frame at runtime
                //frame.descTextView.text = "Section $i" // change textView on frame ( use string resources! not hardcoded string like me)
                
                binding.gridVote.addView(frame) //exp : 만들어 놓은 frame을 grid_layout에 추가
            }
            Toast.makeText(this.getApplicationContext() , "게시물을 추가했습니다." , Toast.LENGTH_SHORT).show()
        }
    }

    //exp : 게시물이 추가되면 게시물을 GridLayout에 추가
    //TODO : ActivityUploadPost에서 게시물이 만들어지면 반응해서 추가해야 하므로 ActivityUploadPost에서 구현
    fun AddNewPosting()
    {
    }

    fun MakeGridLayout()
    {
        //TODO : 이미지의 height는 비율로 가져오자
    }
}