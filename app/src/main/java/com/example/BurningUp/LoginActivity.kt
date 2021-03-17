package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        binding.loginBtn.setOnClickListener{

            if(binding.usernameEt.text.trim().isNotEmpty()&&binding.passwordEt.text.trim().isNotEmpty()){
                signInUser();
            }else{
                Toast.makeText(this, "아이디 or 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }

        binding.btnGoChatroom.setOnClickListener {
            val intent_go_add_chat_room = Intent(this,AddChatRoomActivity::class.java)
            startActivity(intent_go_add_chat_room)
        }

    }

    fun signInUser(){
        auth.signInWithEmailAndPassword(binding.usernameEt.text.trim().toString(), binding.passwordEt.text.trim().toString())
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    val intent = Intent(this,MainActivity::class.java);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Authentication Error "+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;
        if(user!=null){
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
        }else{
            Toast.makeText(this,"User first time login", Toast.LENGTH_LONG).show()
        }
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

}