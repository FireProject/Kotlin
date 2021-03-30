package com.example.BurningUp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityChangeProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.security.Key
import java.util.HashMap

class ChangeProfileActivity : AppCompatActivity()
{
    private var mBinding: ActivityChangeProfileBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth : FirebaseAuth
    private var uid : String? = null
    private lateinit var firebase : FirebaseDatabase
    private lateinit var users_test_ref : DatabaseReference //연습용
    private lateinit var user_ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChangeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth?.uid
        firebase = FirebaseDatabase.getInstance()
        /*
        TODO : 나중에 다른 액티비티에서 사용
        users_test_ref = firebase.getReference("usersTest").child(uid.toString()).child("nickname")
        GetUserDataPractice()
        */

        user_ref = firebase.getReference("usersTest").child(uid.toString())
        ChangePhoto()
        ChangeUserData()
        ReturnMainActivity()
    }
    //TODO : 사진은 추후에
    fun ChangePhoto()
    {
        binding.circleview.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "사진 추가 기능 구현하세요." , Toast.LENGTH_SHORT).show()
        }
    }
    fun ChangeUserData()
    {
        binding.btnConfirm.setOnClickListener{
            user_ref.child("nickname").setValue(binding.editTextTextPersonName.text.toString())
            user_ref.child("determination").setValue(binding.etDetermination.text.toString())
            Toast.makeText(this.getApplicationContext() , "프로필 변경이 완료되었습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }

    fun ReturnMainActivity()
    {
        binding.btnReturnMain.setOnClickListener {
            Toast.makeText(this.getApplicationContext() , "프로필 변경을 취소하셨습니다." , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }
    }
    fun GetUserDataPractice()
    {
        val map = HashMap<Any, Any>()
        users_test_ref.get().addOnSuccessListener{
            Log.d("jiwon" , "${it.value}") //exp : 공식 Kotlin 형식
            Log.d("jiwon" , it.getValue().toString()) //exp : getValue()를 이용하는 형식 -> 이게 익숙
            map.put("key" , it.getValue().toString()) //exp : it가 DataSnapshot이므로 , getValue의 return type도 Datasnapshot이므로 string으로
        }
    }

    /*
    private fun AddPostEventListener(postReference: DatabaseReference)
    {
        // [START post_value_event_listener]
        val postListener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                val post = dataSnapshot.getValue()
                Log.d("jiwon" , post.toString());

            }

            override fun onCancelled(databaseError: DatabaseError)
            {
                // Getting Post failed, log a message
                Log.w("jiwon", "loadPost:onCancelled", databaseError.toException())
            }
        }
        postReference.addValueEventListener(postListener)
        // [END post_value_event_listener]
    }*/


}