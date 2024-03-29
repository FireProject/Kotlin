package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BurningUp.Friends.Companion.friends_constainer
import com.example.BurningUp.databinding.ActivityProfileListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile_list.*

class ProfileList : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener {

    private var mBinding: ActivityProfileListBinding? = null
    private val binding get() = mBinding!!

    private lateinit var homeFragment: HomeFragment
    //private lateinit var profileFragment: ProfileFragment
    //private lateinit var chatFragment: ChatListFragment

    private lateinit var auth: FirebaseAuth
    private var uid: String? = null
    private lateinit var firebase: FirebaseDatabase
    private lateinit var users_ref: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)

        var profileList= arrayListOf<Friends.Companion.Info>()  //프로필 리스트 배열 선언


       // profileList.clear()
        //함수호출
        Users.readInfo()
        Friends.GetFriends()
        //DB초기화
        auth = FirebaseAuth.getInstance()
        uid=auth?.uid
        firebase= FirebaseDatabase.getInstance()
        users_ref=firebase.getReference("users").child(uid.toString())  //루트의 자식으로 "users" 연결->DB 테이블 연결

            //0번째 배열에 user 이름 넣어주고싶었는데 왜 안되지ㅜㅜ

        profileList[0].nickName=Users.info.nickName
        profileList[0].stateMessage=Users.info.stateMessage

        //profileList.clear()
        for(obj in friends_constainer)
        {
            profileList.add(obj)
        }

        rv_profile.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = ProfileAdapter(profileList)

    }



    //바닥에 네비게이션 바

    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId)
        {       //각각의 버튼마다 버튼이 나오면 화면이 나오도록 해둠
            R.id.menu_home->{
                homeFragment= HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,homeFragment).commit()
            }
            R.id.menu_profile->{
                val intent= Intent(this,ProfileList::class.java)
                startActivity(intent)
            }
            R.id.menu_chat->{
                val intent = Intent(this,ChatListActivity::class.java)
                startActivity(intent)
            }

        }
    }


}