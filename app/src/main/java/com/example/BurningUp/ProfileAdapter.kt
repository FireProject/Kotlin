package com.example.BurningUp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter( val profileList: ArrayList<Friends.Companion.Info>):RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {
        //리스트 아이템들을 붙이는 작업
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_itme,parent,false)
        return CustomViewHolder(view).apply {
            //프로필 클릭처리를 위해
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition  //현재 위치를 저장하는 변수
                val Profile: Friends.Companion.Info =profileList.get(curPos)
                    //함수로 이름이랑 상태메시지, 프사 인자로 넘겨주고  intent 해줘야 할듯?
               Toast.makeText(parent.context,"이름: ${Profile.nickName}  상태메시지: ${Profile.stateMessage}",Toast.LENGTH_SHORT).show()

            }

        }

    }

    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {

        //프로필 사진의 경우
        //holder.profile.setImagResource(ProfileList.get(position).profile)
        holder.name.text = profileList.get(position).nickName
        holder.statemessage.text=  profileList.get(position).stateMessage
        //레벨 등 숫자로 입력 받을경우에는 위에 똑같이 쓰고 마지막에 .toString()써주기
    }

    override fun getItemCount(): Int {
       return profileList.size
    }

    //뷰 연결할 때 사용할거임
    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        //여기에 프로필 사진이랑 레벨 추가하기
        //현재는 이름이랑 상태 메시지만 추가해둠
        val name=itemView.findViewById<TextView>(R.id.tv_name)
        val statemessage =itemView.findViewById<TextView>(R.id.tv_statemessage)
    }

}