package com.example.BurningUp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment:Fragment() {

    //프로필 리스트 보기 프래그먼트

    //데이터를 담을 그릇
    var modelList = ArrayList<Profiles>()
    private lateinit var profileAdapater: ProfileAdapater


    companion object {

        const val TAG: String = "logg"
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "ProfileFrag_oncreate() called")

        for (i in 1..10) {
            val profiles = Profiles(null, "조민영$i", "hello $i")
            this.modelList.add(profiles)
        }


        //어답터 인스턴스 생성
        profileAdapater= ProfileAdapater()
        profileAdapater.submitList(this.modelList)


        friendlist_recyclerview.apply{
            //리사이클러 방향 등 설정
            //layoutManager= LinearLayoutManager(this@profileFragment, LinearLayoutManager.VERTICAL, false)
            // 어댑터 장착
            adapter=profileAdapater
        }
    }



}
    //
    // 프래그먼트가 메인 액티비티에 붙게되는(?)
    //프래그먼트를 안고 있는 액티비티에 붙었을 때
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d(TAG, "ProfileFrag_oncreate() called")
//    }

    //뷰가 생성 되었을 때
    //프레그먼트와 레이아웃을 연결시켜주는 부분이다.

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Log.d(TAG, "ProfileFrag_oncreate() called")
//        val view=inflater.inflate(R.layfriendlist_recyclerviewout.fragment_profile,container,false)
//
//        return view
//    }
//}