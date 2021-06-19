package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
        MoveAddChatRoomActivity(view);

        return view
    }

    //이거 추가해서 괜찮.
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        //test
        val first_room_name = Rooms.rooms_contain_specific_user[0].room_name;
        val first_cur_cnt = Rooms.rooms_contain_specific_user[0].cur_person.toString();
        val first_max_cnt = Rooms.rooms_contain_specific_user[0].max_person.toString();
        //exp : Rooms의 자료구조에 저장한 데이터를 여기서 사용합니다.
        val chatlist = arrayListOf(
                ChatListOutlineValue(R.drawable.black_smile,first_room_name,"("+first_cur_cnt+"/"+first_max_cnt+")", "아직 DB에 없음")
        )
        rv_chatlist.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
        rv_chatlist.setHasFixedSize(true);
        rv_chatlist.adapter = ChatListAdapter(chatlist);
    }

    fun MoveAddChatRoomActivity(view : View)
    {
        view.btn_go_add_chat_room.setOnClickListener {
            Toast.makeText(activity, "채팅방을 만들어 봅시다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, AddChatRoomActivity::class.java);
            startActivity(intent)
        }
    }
}
