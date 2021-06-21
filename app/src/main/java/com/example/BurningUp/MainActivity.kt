package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener,NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileListFragment: profileListFragment
  //  private lateinit var changeProfileFragment: ChangeProfileFragment
   // private lateinit var changePasswordFragment: ChangePasswordFragment
    private lateinit var settingAlarmFragment: SettingAlarmFragment
    private lateinit var introduceProgrammerFragment: IntroduceProgrammerFragment

    private lateinit var auth : FirebaseAuth
    private lateinit var mdatabase : FirebaseDatabase
    private lateinit var mRef : DatabaseReference

    private lateinit var header : View

    companion object{
        const val TAG:String="로그"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        auth = FirebaseAuth.getInstance()

        //레이아웃과 연결
        setContentView(R.layout.activity_main)

        Log.d("mmm", "MainActivity_oncreate() called")


        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START)   //start는 왼쪽에서 열거라는거
        }

        navi_view.setNavigationItemSelectedListener(this)

        //네비게이션 메뉴 아이템에 클릭 속성 부여
        //bottom_nav.setOnNavigationItemReselectedListener(this)
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.menu.getItem(1).isChecked = true
        //처음에는 홈화면이 나오도록 함 
        homeFragment= HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame,homeFragment).commit()

    }


    override fun onNavigationItemReselected(item: MenuItem) {   //바닥에 있는 네비게이션
        Log.d("mmm", "MainActivity_onNavi() called")

        when(item.itemId)
        {       //각각의 버튼마다 버튼이 나오면 화면이 나오도록 해둠
            R.id.menu_home->{
                Log.d("naviItemBUtton", "MainActivity_홈버튼 ")
                topBar.isVisible = true
                homeFragment= HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,homeFragment).commit()
            }
            R.id.menu_profile->{
//                val intent=Intent(this,ProfileList::class.java)
//                startActivity(intent)
                topBar.isVisible = false
                profileListFragment= com.example.BurningUp.profileListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,profileListFragment).commit()
                //rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
               // supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,profileFragment).commit()

           }
            //exp : go ppt_8
            R.id.menu_chat->
            {
                /*val intent =Intent(this,ChatListActivity::class.java)
                startActivity(intent)*/

                // 프래그먼트로 이동
                topBar.isVisible = false
                val fragment_manager = supportFragmentManager.beginTransaction()
                fragment_manager.replace(R.id.fragments_frame , ChatListFragment()).commit()
                //exp : replace : 교체 , commit : 저장
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem) :Boolean{ //메뉴버튼 눌렀을 때 네비게이션 수행

        when(item.itemId)
        {
            R.id.chprofile->{
                val intent =Intent(this,ChangeProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.chpassword->{
                val intent =Intent(this,ChangePasswordActivity::class.java)
                startActivity(intent)
            }

            R.id.introuduce_p->{
                introduceProgrammerFragment=IntroduceProgrammerFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,introduceProgrammerFragment).commit()
            }

            R.id.logout_btn->{
                auth.signOut()

                val intent = Intent(this, LoginActivity::class.java);
                startActivity(intent)//로그아웃 후 로그인 화면으로 이동
            }

            R.id.menu_home->{
                Log.d("naviItemBUtton", "MainActivity_홈버튼 ")
                topBar.isVisible = true
                item.isChecked = true
                homeFragment= HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,homeFragment).commit()
            }
            R.id.menu_profile->{
//                val intent=Intent(this,ProfileList::class.java)
//                startActivity(intent)
                topBar.isVisible = false
                item.isChecked = true
                profileListFragment= com.example.BurningUp.profileListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,profileListFragment).commit()
                //rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                // supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,profileFragment).commit()

            }
            //exp : go ppt_8
            R.id.menu_chat->
            {
                /*val intent =Intent(this,ChatListActivity::class.java)
                startActivity(intent)*/

                // 프래그먼트로 이동
                topBar.isVisible = false
                item.isChecked = true
                val fragment_manager = supportFragmentManager.beginTransaction()
                fragment_manager.replace(R.id.fragments_frame , ChatListFragment()).commit()
                //exp : replace : 교체 , commit : 저장
            }
        }

        layout_drawer.closeDrawers()
        return  false

    }

    override fun onBackPressed() {  //안드로이드에서 제공되는 백버튼이 입력되었을때

        if(layout_drawer.isDrawerOpen(GravityCompat.START)) //서랍이 열려있을때는 서랍을 닫아주도록
        {
            layout_drawer.closeDrawers()
        }
        else //서랍이 열려있지 않으면
        {
            super.onBackPressed()
            //로그인화면으로 돌아가지 않게 버튼 막기
        }
    }

}



