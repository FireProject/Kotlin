package com.example.BurningUp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatListAdapter(val chatlist : ArrayList<ChatListOutlineValue>) : RecyclerView.Adapter<ChatListAdapter.CustomViewHolder>()
{
    //exp : oncreate와 유사하게 xml 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListAdapter.CustomViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chatlist_outline , parent , false);  //xml 정보 읽기
        return CustomViewHolder(view); //생성자로 인자를 넣어줍니다.
    }

    //exp : ListView의 index 개수
    override fun getItemCount(): Int
    {
        return chatlist.size;
    }

    //exp : 계속 호출되며 RecycleView 갱신
    override fun onBindViewHolder(holder: ChatListAdapter.CustomViewHolder, position: Int)
    {
        holder.chatlist_img.setImageResource(chatlist.get(position).chatlist_img);
        holder.chatroom_name.text = chatlist.get(position).chatroom_name;
        holder.chatroom_member_cnt.text = chatlist.get(position).chatroom_member_cnt;
        holder.chatroom_cur_msg.text = chatlist.get(position).chatroom_cur_msg;
    }

    //exp : RecyclerView 상속받은 custom
    //TODO : 인원수 3/15 는 3을 아마 DB에서 계속 갱신해가며 체크해야 합니다.
    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val chatlist_img = itemView.findViewById<ImageView>(R.id.img_recycle_chatlist);
        val chatroom_name = itemView.findViewById<TextView>(R.id.tv_recycle_chatroom_name);
        val chatroom_member_cnt = itemView.findViewById<TextView>(R.id.tv_recycle_chatroom_member_cnt);
        val chatroom_cur_msg = itemView.findViewById<TextView>(R.id.tv_recycle_chatroom_current_message);
    }


}