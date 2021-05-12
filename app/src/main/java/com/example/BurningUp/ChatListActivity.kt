package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.fragment_chat_list.*
import kotlinx.android.synthetic.main.fragment_chat_list.btn_go_add_chat_room
import kotlinx.android.synthetic.main.fragment_chat_list.rv_chatlist
import kotlinx.android.synthetic.main.fragment_chat_list.view.*

/*
TODO : 이거는 이제 사용하지 않아요!!!
 */
class ChatListActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        //TODO : DB에서 읽어오면 , 동적배열로 구현하고 추가.
        val chatlist = arrayListOf(
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_1","11/15" , "a")
        )

        rv_chatlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false); //여기서 에러
        rv_chatlist.setHasFixedSize(true);
        rv_chatlist.adapter = ChatListAdapter(chatlist);

        MoveAddChatRoomActivity();
        ReturnMainActivity()
    }

    fun ReturnMainActivity()
    {
        btn_return_main_at_chatlist.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }
    fun MoveAddChatRoomActivity()
    {
        btn_go_add_chat_room.setOnClickListener {
            Toast.makeText(this, "채팅방을 만들어 봅시다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddChatRoomActivity::class.java);
            startActivity(intent)
        }
    }
}