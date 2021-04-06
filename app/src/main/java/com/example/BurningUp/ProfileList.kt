package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile_list.*

class ProfileList : AppCompatActivity(),BottomNavigationView.OnNavigationItemReselectedListener {

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

        Friends.Companion.info

        var ProfileList= arrayListOf<Profiles>()
        //DB초기화
        auth = FirebaseAuth.getInstance()
        uid=auth?.uid
        firebase= FirebaseDatabase.getInstance()
        users_ref=firebase.getReference("users/freinds")   //루트의 자식으로 "users" 연결->DB 테이블 연결


        Friends.GetValue(users_ref)


        var tmp:Array<String>

        users_ref.addValueEventListener(object: ValueEventListener{


            override fun onDataChange(snapshot: DataSnapshot) {
                for(DataSnapshot in snapshot.children)
                {
                    tmp = DataSnapshot as Array<String>
                    Log.d("Minyoung",tmp.toString())
                }
            }



            override fun onCancelled(error: DatabaseError) {

            }

        })

        //Log.d("Minyoung", tmp.toString())

//            val profileList= arrayListOf(
//
//            Profiles("조민영","메롱"),
//            Profiles("이정진","메롱"),
//            Profiles("최종선","메롱"),
//            Profiles("김미미","메롱"),
//            Profiles("황규백","메롱"),
//            Profiles("신용태","메롱")
//
//
//       )
       // Log.d("Minyoung",tmp.toString() )

        rv_profile.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)

     //   rv_profile.adapter = ProfileAdapter(profileList)
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

                val intent = Intent(this,ChatListActivity::class.java)
                startActivity(intent)
//                chatFragment= ChatListFragment()
//                supportFragmentManager.beginTransaction().replace(R.id.fragments_frame,chatFragment).commit()
            }

        }
    }


}