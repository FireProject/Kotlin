package com.example.BurningUp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleChatroomAdapter(val chatroom_list : ArrayList<RecycleChatroom>) : RecyclerView.Adapter<RecycleChatroomAdapter.CustomViewHolder>()
{
    //exp : oncreate와 유사하게 xml 연결
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleChatroomAdapter.CustomViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_chatroom_list , parent , false); //activity와 연결된 모든 정보를 가져옵니다.
        return CustomViewHolder(view); //생성자로 인자를 넣어줍니다.
    }

    //exp : ListView의 index 개수
    override fun getItemCount(): Int
    {
        return chatroom_list.size;
    }

    //exp : 계속 호출
    override fun onBindViewHolder(holder: RecycleChatroomAdapter.CustomViewHolder, position: Int)
    {
        holder.txt_1.text = chatroom_list.get(position).a;
        holder.txt_2.text = chatroom_list.get(position).b;
    }

    //exp : RecyclerView 상속받은 custom
    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val txt_1 = itemView.findViewById<TextView>(R.id.tv_test_11);
        val txt_2 = itemView.findViewById<TextView>(R.id.tv_test_22);
    }
}