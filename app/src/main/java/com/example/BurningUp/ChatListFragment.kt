package com.example.BurningUp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BurningUp.R.drawable.black_smile
import com.example.BurningUp.databinding.FragmentChatListBinding
import kotlinx.android.synthetic.main.activity_profile_list.*
import kotlinx.android.synthetic.main.activity_test_recycle.*
import kotlinx.android.synthetic.main.fragment_chat_list.*
import kotlinx.android.synthetic.main.fragment_chat_list.view.*

//TODO : 채팅방 한칸의 recycler view의 height는 핸드폰에 맞추기 , 현재는 그냥 120dp 함
class ChatListFragment : Fragment()
{
    //ref : 홍드로이드 -> https://duckssi.tistory.com/42
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        //exp : fragment_chat_list(xml)과 연결시켜줍니다.
        var view = inflater.inflate(R.layout.fragment_chat_list , container , false)

        //여기에 DB 추가 -> 이게 MainActivity
//        val chatlist = arrayListOf(
//                ChatListOutlineValue(R.drawable.black_smile,"a","b"),
//                ChatListOutlineValue(R.drawable.loading_image,"c","d"),
//                ChatListOutlineValue(R.drawable.fire_mini,"e","ggggg")
//        )
//        Log.d("jiwon" , "ERROR_1");
//        //rv_chatlist.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false); //여기서 에러
//        Log.d("jiwon" , "ERROR_2");
//        rv_chatlist.setHasFixedSize(true);
//        Log.d("jiwon" , "ERROR_3");
//        rv_chatlist.adapter = ChatListAdapter(chatlist);
//        Log.d("jiwon" , "ERROR_4");

     //   MoveAddChatRoomActivity(view)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val chatlist = arrayListOf(
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_1","11/15" , "a"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_2","10/15" , "b"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_3","9/15" , " 안녕하세요 안녕하세요안녕하세요 안녕하세요안녕하세요 안녕하세요안녕하세요 안녕하세요안녕하세요 안녕하세요"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_4","8/15" , "d"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_5","7/15" , "e"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_6","6/15" , "f"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_7","5/15" , "g"),
                ChatListOutlineValue(R.drawable.black_smile,"취업 준비방_8","4/15" , "h")
        )

        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = ChatListAdapter(chatlist);

    }
//    fun MoveAddChatRoomActivity(view : View)
//    {
//        view.btn_go_add_chat_room.setOnClickListener {
//            Toast.makeText(activity, "채팅방을 만들어 봅시다." , Toast.LENGTH_SHORT).show()
//            val intent = Intent(activity, AddChatRoomActivity::class.java);
//            startActivity(intent)
//        }
//    }
}
