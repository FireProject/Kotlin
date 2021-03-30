package com.example.BurningUp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment:Fragment() {

    companion object{

        const val TAG: String ="logg"
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HoemFrag_oncreate() called")
    }

    // 프래그먼트가 메인 액티비티에 붙게되는(?)
    //프래그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "HoemFrag_oncreate() called")
    }

    //뷰가 생성 되었을 때
    //프레그먼트와 레이아웃을 연결시켜주는 부분이다.

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "HoemFrag_oncreate() called")
        val view=inflater.inflate(R.layout.fragment_profile,container,false)

        return view
    }
}