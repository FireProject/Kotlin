package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.BurningUp.databinding.FragmentChatListBinding
import kotlinx.android.synthetic.main.fragment_chat_list.view.*

//TODO : 채팅방 한칸의 recycler view의 height는 핸드폰에 맞추기 , 현재는 그냥 120dp 함
class ChatListFragment : Fragment()
{
    //ref : 홍드로이드 -> https://duckssi.tistory.com/42
    private var mBinding: FragmentChatListBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        mBinding = FragmentChatListBinding.inflate(inflater, container, false)

        //exp : fragment_chat_list(xml)과 연결시켜줍니다.
        var view = inflater.inflate(R.layout.fragment_chat_list , container , false)
        MoveAddChatRoomActivity(view)

        return view
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
