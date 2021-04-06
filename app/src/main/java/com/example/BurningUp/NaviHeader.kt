package com.example.BurningUp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.NaviHeaderBinding

class NaviHeader : AppCompatActivity(){
    private var mBinding: NaviHeaderBinding? = null
    private val binding get() = mBinding!!

    private val TAG:String="예진"
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        mBinding = NaviHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "hello")
        binding.welcomeTv.text= "hello"
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "hello")
    }
}