package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_ssival.*

class SsivalActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var chatFragment: ChatFragment

    companion object{
        const val TAG:String="로그"
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //레이아웃과 연결결
       setContentView(R.layout.activity_ssival)
        Log.d(TAG, "MainActivity_oncreate() called")

        bottom_nav.setOnNavigationItemReselectedListener(this)
        //처음에는 홈화면이 나오도록 함 
        homeFragment= HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame,homeFragment).commit()

    }

    override fun onNavigationItemReselected(item: MenuItem) {
        Log.d(TAG, "MainActivity_onNavi() called")

        when(item.itemId)
        {       //각각의 버튼마다 버튼이 나오면 화면이 나오도록 해둠
            R.id.menu_home->{
                Log.d(TAG, "MainActivity_홈버튼 ")
                homeFragment= HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,homeFragment).commit()
            }
            R.id.menu_profile->{
                Log.d(TAG, "MainActivity_프로필필버튼 ")
                profileFragment= ProfileFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,profileFragment).commit()
           }
            R.id.menu_chat->{
                Log.d(TAG, "MainActivity_채팅버튼 ")
                chatFragment= ChatFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,chatFragment).commit()
            }
        }


    }
}