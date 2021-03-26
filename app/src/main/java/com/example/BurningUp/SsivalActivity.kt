package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_ssival.*

class SsivalActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener,NavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var chatFragment: ChatFragment
    private lateinit var changeProfileFragment: ChangeProfileFragment
    private lateinit var changePasswordFragment: ChangePasswordFragment
    private lateinit var settingAlarmFragment: SettingAlarmFragment
    private lateinit var introduceProgrammerFragment: IntroduceProgrammerFragment


    private lateinit var auth : FirebaseAuth

    companion object{
        const val TAG:String="로그"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        //레이아웃과 연결결
       setContentView(R.layout.activity_ssival)
        Log.d(TAG, "MainActivity_oncreate() called")


        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START)   //start는 왼쪽에서 열거라는거
        }
        navi_view.setNavigationItemSelectedListener(this)  //네비게이션 메뉴 아이템에 클릭 속성 부여


        bottom_nav.setOnNavigationItemReselectedListener(this)
        //처음에는 홈화면이 나오도록 함 
        homeFragment= HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame,homeFragment).commit()

    }

    override fun onNavigationItemReselected(item: MenuItem) {   //바닥에 있는 네비게이션
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

    override fun onNavigationItemSelected(item: MenuItem) :Boolean{    //메뉴버튼 눌렀을 때 네비게이션 수행
        when(item.itemId)
        {
            R.id.chprofile->{
                changeProfileFragment=ChangeProfileFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,changeProfileFragment).commit()

            }
            R.id.chpassword->{
               changePasswordFragment= ChangePasswordFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,changePasswordFragment).commit()

            }
            R.id.alarm->{
                settingAlarmFragment= SettingAlarmFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,settingAlarmFragment).commit()

            }
            R.id.introuduce_p->{
                /*introduceProgrammerFragment=IntroduceProgrammerFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,introduceProgrammerFragment).commit()*/
                val intent = Intent(this, AddChatRoomActivity::class.java);
                startActivity(intent)//로그아웃 후 로그인 화면으로 이동

            }
            R.id.logout_btn->{
                auth.signOut()

                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent)//로그아웃 후 로그인 화면으로 이동
            }

        }

        layout_drawer.closeDrawers()
        return  false

    }

    override fun onBackPressed() {  //안드로이드에서 제공되는 백버튼이 입려되었을때

        if(layout_drawer.isDrawerOpen(GravityCompat.START)) //서랍이 열려있을때는 서랍을 닫아주도록
        {
            layout_drawer.closeDrawers()
        }
        else //서랍이 열려있지 않으면
        {
            super.onBackPressed()
        }
    }
}

