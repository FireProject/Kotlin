package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        val chatlist = arrayListOf(
            ChatListOutlineValue("a","b"),
            ChatListOutlineValue("c","d"),
            ChatListOutlineValue("e","ggggg")
        )
        Log.d("jiwon" , "ERROR_1");
        rv_chatlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false); //여기서 에러
        Log.d("jiwon" , "ERROR_2");
        rv_chatlist.setHasFixedSize(true);
        Log.d("jiwon" , "ERROR_3");
        rv_chatlist.adapter = ChatListAdapter(chatlist);
        Log.d("jiwon" , "ERROR_4");
    }
}