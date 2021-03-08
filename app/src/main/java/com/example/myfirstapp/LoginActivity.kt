package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirstapp.databinding.ActivityLoginBinding
import com.example.myfirstapp.databinding.ActivityMainBinding
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
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerTv.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }

    }

    fun signInUser(){
        auth.signInWithEmailAndPassword(binding.usernameEt.text.trim().toString(), binding.passwordEt.text.trim().toString())
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    val intent = Intent(this,DashboardActivity::class.java);
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
            val intent = Intent(this, DashboardActivity::class.java);
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