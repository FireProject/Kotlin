package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myfirstapp.databinding.ActivityLoginBinding
import com.example.myfirstapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private var mBinding: ActivityRegisterBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener{
            if(binding.emailEdit.text.isNotEmpty()&&binding.passwordEdit.text.isNotEmpty()&&binding.passwordEditC.text.isNotEmpty()){
                registerUser();
            }else{
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }

        binding.loginTv.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java);
            startActivity(intent);
        }

    }

    fun registerUser(){

        auth.createUserWithEmailAndPassword(binding.emailEdit.text.trim().toString(), binding.passwordEdit.text.trim().toString())
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){

                    Toast.makeText(this, "Register successful", Toast.LENGTH_LONG).show()

                }else{

                    Toast.makeText(this, "Register failed " + task.exception, Toast.LENGTH_LONG).show()

                }
            }
    }

    override fun onStart(){
        super.onStart()

        val user = auth.currentUser;
//
//        if(user != null){
//            val intent = Intent(this, DashboardActivity::class.java);
//            startActivity(intent)
//        }else{
//            Log.e("user status", "User null")
//        }
    }
}