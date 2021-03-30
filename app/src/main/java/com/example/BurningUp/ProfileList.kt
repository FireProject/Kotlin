package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_profile_list.*

class ProfileList : AppCompatActivity() {
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
}