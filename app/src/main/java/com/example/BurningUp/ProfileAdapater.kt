package com.example.BurningUp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
                        //profileList=생성자                   리사이클러뷰의 adapter 상속 받음, custiomviewholder라는 클래스를 만들어줌
class ProfileAdapater(val profileList: ArrayList<Profiles>):RecyclerView.Adapter<ProfileAdapater.CustomViewHolder>()
{
    //list_item를 붙이는 작업을 한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapater.CustomViewHolder {

        //레이아웃인플레리터 통해서 붙이는 작업을 해줄거임 inflate=붙여준다.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_itme, parent,false)
        return CustomViewHolder(view).apply {
            //버튼 클릭하면 나오도록 클릭처리
            itemView.setOnClickListener {
                val curPos:Int=adapterPosition //현재 클릭한 부분의 포지션
                val profile: Profiles=profileList.get(curPos)   //위치 값에있는 데이터(이름,레벨, 프사 이런것들 )를 가져옴
                Toast.makeText(parent.context,"이름:${profile.name} \n상태메시지:${profile.state}",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onBindViewHolder(holder: ProfileAdapater.CustomViewHolder, position: Int) {
        //oncreate로 만들어진 뷰들을  실제 연결을 해준다.

        holder.profile.setImageResource(profileList.get(position).profile)//현재 클릭한 위치와 바로 연동
        holder.name.text=profileList.get(position).name
        holder.state.text=profileList.get(position).state
        holder.level.setImageResource(profileList.get(position).profile)
    }

    override fun getItemCount(): Int {  //리스트들의 총 갯수를 리턴해준다.
        return profileList.size
    }

    class CustomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){  //recycler viewholder 상속받음
        //profiles에 구성되어있는 모델들을 활용한다.
        //findviewById는 특정 xml에서 찾아옴 <>에는 뷰에대한 타입을, ()에는 실제 id를 적어주어서 연동시킨다.
        val profile=itemView.findViewById<ImageView>(R.id.iv_profile) //프로필 사진
        val name=itemView.findViewById<TextView>(R.id.tv_name)  //이름

        val state=itemView.findViewById<TextView>(R.id.tv_statemessage) //상태 메시지
        val level=itemView.findViewById<ImageView>(R.id.iv_level)   //불 크기

    }
}
