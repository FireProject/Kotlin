package com.example.BurningUp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_profile_list.*
import kotlinx.android.synthetic.main.activity_profile_list.rv_profile
import kotlinx.android.synthetic.main.fragment_profile_list.*

class profileListFragment : Fragment() {

     private val linearLayoutManager by lazy {LinearLayoutManager(context)}

    private lateinit var auth: FirebaseAuth
    private var uid: String? = null
    private lateinit var firebase: FirebaseDatabase
    private lateinit var users_ref: DatabaseReference

    companion object {
        fun newInstance(): profileListFragment {
            return profileListFragment()
        }
    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//    }

    var profileList = arrayListOf<Friends.Companion.Info>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile_list, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // var profileList = arrayListOf<Friends.Companion.Info>()
        Log.d("mmm", "profileList's Database is  called()")
        profileList.clear()

        Users.readInfo()
        Friends.GetFriends()
        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance()
        users_ref =firebase.getReference("users").child(uid.toString())  //루트의 자식으로 "users" 연결->DB 테이블 연결

        rv_profile.layoutManager=linearLayoutManager

        for (obj in Friends.friends_constainer) {
            profileList.add(obj)
        }

      //  rv_profile.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = ProfileAdapter(profileList)


    }
}