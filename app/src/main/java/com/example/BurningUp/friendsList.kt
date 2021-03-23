package com.example.BurningUp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BurningUp.databinding.ActivityAddChatRoomBinding
import com.example.BurningUp.databinding.ActivityFriendslistBinding

//import kotlinx.android.synthetic.main.activity_friendslist.*

class friendsList : AppCompatActivity() {
    private var mBinding: ActivityFriendslistBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFriendslistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvProfile;


        val profileList= arrayListOf(
                //여기 디비로 읽어오면 될듯 ??
                Profiles(R.drawable.profile,"조민영","안드로이드 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"이정진","ios 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"최종선","유니티 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"김마미","미분적분 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"박지원","컴수 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"장대호","리액트 개발",R.drawable.fire_mini),
                Profiles(R.drawable.profile,"박예진","알고리즘 개발",R.drawable.fire_mini)
        )
        // layoutmanager는 리사이클러 뷰 사용하려면 연결해줘야하는 애임
    }


}