package com.example.BurningUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityChangePasswordBinding
import com.example.BurningUp.databinding.ActivityFindFriendsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FindFriendsActivity : AppCompatActivity() {

    private var mBinding: ActivityFindFriendsBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth: FirebaseAuth
    private var uid: String? = null
    private lateinit var firebase: FirebaseDatabase
    private lateinit var users_ref: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)

        mBinding= ActivityFindFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        Log.d("mmm","FindsFriendsActivity is called")   //로그 찍힘

        // 돋보기 버튼이 클릭되었을 때
        binding.searchBtn.setOnClickListener {

            Log.d("mmm","search Button is Clicked")


            if(binding.findFriends.text.isNotEmpty())   //친구 찾기 목록에 뭔가 입력되어있으면
            {
                val email = binding.findFriends.text

                var emailHex = ""
                //.text로 접근해서 받아들여진거 읽고 16진수로 변환 후 친구찾ㄷ기
                for (c in email) {
                    val cc = c.toInt()
                    val str = String.format("%02x",cc)
                    emailHex += str
                }
                Log.d("mmmm", "string to hex ${emailHex}")

             //  users_ref.reference("emailToUid/${emailHex}")
                auth = FirebaseAuth.getInstance()
                uid = auth?.uid
              //  firebase = FirebaseDatabase.getInstance()
                users_ref = Firebase.database.reference


                users_ref.child("emailToUid/${emailHex}").get().addOnSuccessListener {

                    Log.i("firebase", "Got value ${it.key.toString()}")
                    Log.i("firebase", "Got value ${it.getValue().toString()}")
                    val friendUid = it.getValue().toString()
                    //친구 성공적으로 읽어옴
                    //친구 uid를 Users의 Friends에 친구 추가해주기
                    if (it.value == null) {
                        Toast.makeText(this,"해당 ID의 사용자가 없습니다. ",Toast.LENGTH_SHORT).show()
                    } else {
                        //본인이거나, 이미 친구인경우 추가하면 안됨.
                            val tmp = Users.info.friends
                        if (friendUid == uid) {
                            return@addOnSuccessListener
                        }
                        for(uid in tmp) {
                            if (uid == friendUid) {
                                return@addOnSuccessListener
                            }
                        }

                        tmp.add(friendUid)
                        val tmpRef = Firebase.database.reference
                        tmpRef.child("users/${uid}/friends").setValue(tmp)

                        Toast.makeText(this, "000님과 친구가 되었습니다. ",Toast.LENGTH_SHORT).show()
                    }



                    //친구 추가가 성공적으로 되었으면

                }.addOnFailureListener{
                    //친구 못읽음

                    Log.e("firebase", "Error getting data", it)
                }
            }

        }


}



}