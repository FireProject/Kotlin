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

        binding.loginBtn.setOnClickListener{//로그인버튼 클릭시

            if(binding.usernameEt.text.trim().isNotEmpty()&&binding.passwordEt.text.trim().isNotEmpty()){ //입력 창에 모두 입력시
                signInUser()
            }else{
                Toast.makeText(this, "아이디 or 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerBtn.setOnClickListener {//회원가입 버튼 클릭시
            val intent = Intent(this, Register1Activity::class.java);
            startActivity(intent)//회원가입 화면으로 이동
        }

        binding.changePasswordBtn.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java);
            startActivity(intent)//회원가입 화면으로 이동
        }

    }

    fun signInUser(){//로그인 함수
        auth.signInWithEmailAndPassword(binding.usernameEt.text.trim().toString(), binding.passwordEt.text.trim().toString()) //이메일 비밀번호 인증
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){//로그인 성공시
                    if(auth.currentUser.isEmailVerified){//인증된 계정이면
                        val intent = Intent(this,SsivalActivity::class.java);
                        startActivity(intent);//메인화면으로 이동
                    }else{//아직 인증안한 계정일 경우 로그인 하지 않고 인증하라는 메시지 출력
                        Toast.makeText(this, "Please verify your email address", Toast.LENGTH_LONG).show()
                    }
                }else{//로그인 실패
                    Toast.makeText(this, "Authentication Error "+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {//자동 로그인 함수
        super.onStart()
        val user = auth.currentUser
        if(user!=null){ //로그인 되어있으면 바로 메인 화면으로 이동
            val intent = Intent(this, SsivalActivity::class.java);
            startActivity(intent)
        }else{//로그인 되어있지 않으면
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