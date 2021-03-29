package com.example.BurningUp

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_itme2.view.*

//커스텀뷰홀더
class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    val TAG:String="로그"

    private val usernameTextView=itemView.user_name
    private val profileImageView=itemView.profile_img
    //기본 생성자
    init{
        Log.d(TAG,"MyViewHolder is called")
    }

    fun bind(profiles:Profiles)
    {
        Log.d(TAG,"MyViewHolder - bind() called00")
        usernameTextView.text=profiles.name

    }

}