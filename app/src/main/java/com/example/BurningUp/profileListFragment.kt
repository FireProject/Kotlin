package com.example.BurningUp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BurningUp.databinding.ActivityChangePasswordBinding
import com.example.BurningUp.databinding.FragmentProfileListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_find_friends.*
import kotlinx.android.synthetic.main.activity_profile_list.*
import kotlinx.android.synthetic.main.activity_profile_list.rv_profile
import kotlinx.android.synthetic.main.fragment_profile_list.*

class profileListFragment : Fragment() {

    private var mBinding: FragmentProfileListBinding? = null
    private val binding get() = mBinding!!

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
//        mBinding = FragmentProfileListBinding.inflate(layoutInflater)
//       // setContentView(binding.root)
//        //auth = FirebaseAuth.getInstance()
//
//        binding.addFriendsBtn.setOnClickListener{
//            val intent= Intent(getActivity(), FindFriendsActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile_list, container, false)




        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

          //Users.readInfo()
        //Friends.GetFriends()

        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance()
        users_ref =firebase.getReference("users").child(uid.toString())  //루트의 자식으로 "users" 연결->DB 테이블 연결
        //Log.d("mmm", "Error??")
        rv_profile.layoutManager=linearLayoutManager
        //profileList = Friends.friends_constainer

        profileList.clear()
        var tmp = Friends.Companion.Info()
        tmp.nickName=Users.info.nickName
        tmp.stateMessage=Users.info.stateMessage
        profileList.add(tmp)
        for (obj in Friends.friends_constainer) {
            profileList.add(obj)
        }

      //  rv_profile.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = ProfileAdapter(profileList)




    //이걸 어디에다가 끼워넣어야하지??

//        mBinding = FragmentProfileListBinding.inflate(layoutInflater)
//        // setContentView(binding.root)
//        //auth = FirebaseAuth.getInstance()
//
//        binding.addFriendsBtn.setOnClickListener{
//            val intent= Intent(getActivity(), FindFriendsActivity::class.java)
//            startActivity(intent)
//        }

    }
}
