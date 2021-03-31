package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.GravityCompat
import com.example.BurningUp.databinding.ActivityChangePasswordBinding
import com.example.BurningUp.databinding.ActivityChatRoomBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class ChatRoomActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private var mBinding: ActivityChatRoomBinding? = null
    private val binding get() = mBinding!!
    var imm : InputMethodManager? = null
    private val TAG="FirebaseStorageManager"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
        binding.roomDrawer.setOnClickListener{
            binding.chatRoomDrawer.openDrawer(GravityCompat.END)
        }
        binding.chatRoomNaviView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem) :Boolean { //메뉴버튼 눌렀을 때 네비게이션 수행
        when (item.itemId) {
            R.id.chprofile -> {
                Log.d(TAG, "ChatFrag_oncreate() called")
            }
            R.id.chpassword -> {
                Log.d(TAG, "ChatFrag_oncreate() called")
            }
            R.id.alarm -> {
                Log.d(TAG, "ChatFrag_oncreate() called")
            }
            R.id.introuduce_p -> {
                Log.d(TAG, "ChatFrag_oncreate() called")
            }
            R.id.logout_btn -> {
                Log.d(TAG, "ChatFrag_oncreate() called")
            }

        }
        return false
    }

    fun hideKeyboard(v: View){
        if(v!=null){
            imm?.hideSoftInputFromWindow(v.windowToken,0)
        }
    }


}