package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_profile_list.*

class ProfileList : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener {

    private lateinit var homeFragment: HomeFragment
    // private lateinit var profileFragment: ProfileFragment
    private lateinit var chatFragment: ChatFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)


        val profileList= arrayListOf(

            Profiles("조민영","메롱"),
            Profiles("이정진","메롱"),
            Profiles("최종선","메롱"),
            Profiles("김미미","메롱"),
            Profiles("황규백","메롱"),
            Profiles("신용태","메롱")


        )

        rv_profile.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }


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

                chatFragment= ChatFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,chatFragment).commit()
            }

        }
    }


}