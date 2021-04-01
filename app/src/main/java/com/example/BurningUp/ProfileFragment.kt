package com.example.BurningUp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_profile_list.*
import java.lang.Appendable

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profileList= arrayListOf(

                Profiles("조민영","메롱"),
                Profiles("이정진","메롱"),
                Profiles("최종선","메롱"),
                Profiles("김미미","메롱"),
                Profiles("황규백","메롱"),
                Profiles("신용태","메롱")


        )

        //rv_profile.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter= ProfileAdapter(profileList)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_profile_list, container, false)
    }


}