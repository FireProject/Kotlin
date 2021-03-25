package com.example.BurningUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mdatabase : FirebaseDatabase
    private lateinit var mRef : DatabaseReference


    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        mdatabase = FirebaseDatabase.getInstance()
        mRef = mdatabase.getReference("users")
        var uid = mAuth?.uid

        mRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //값이 변경된게 있으면 database의 값이 갱신되면 자동 호출된다.

                val mValue = snapshot.child("$uid")
                val mNick=mValue.child("nick").value
                binding.textView.text="$mNick"+"님 안녕하세요!"

            }
            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")

            }
        })

        binding.logoutBtn.setOnClickListener{

            mAuth.signOut()

            val intent = Intent(this, LoginActivity::class.java);
            startActivity(intent)

        }

    }


    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}