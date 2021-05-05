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
        //holder.chatlist_img.setImageResource(chatlist.get(position).chatroom_image);
        holder.txt_1.text = chatlist.get(position).a;
        holder.txt_2.text = chatlist.get(position).b;
    }


    //exp : RecyclerView 상속받은 custom
    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        //val chatlist_img = itemView.findViewById<ImageView>(R.id.img_chatroom_profile);
        val txt_1 = itemView.findViewById<TextView>(R.id.tv_test_1);
        val txt_2 = itemView.findViewById<TextView>(R.id.tv_test_2);
    }


}