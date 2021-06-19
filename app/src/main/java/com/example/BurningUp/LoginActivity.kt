package com.example.BurningUp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!

    var imm : InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        val loadingDialog = LoadingDialog(this)

        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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

        val loadingDialog = LoadingDialog(this)

        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d("daeho", "this is signINUser")
        auth.signInWithEmailAndPassword(binding.usernameEt.text.trim().toString(), binding.passwordEt.text.trim().toString()) //이메일 비밀번호 인증
            .addOnCompleteListener(this){
                    task->
                if(task.isSuccessful){//로그인 성공시
                    if(auth.currentUser.isEmailVerified){//인증된 계정이면
                        Users.readInfo()
                        loadingDialog.show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)//메인화면으로 이동
                    }else{//아직 인증안한 계정일 경우 로그인 하지 않고 인증하라는 메시지 출력
                        Toast.makeText(this, "이메일 인증 필요", Toast.LENGTH_LONG).show()
                    }
                }else{//로그인 실패
                    if(!checkInternetConnection()){
                        Toast.makeText(this, "네트워트 연결상태를 확인해주세요",Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this, "잘못된 이메일/비밀번호 입니다", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    override fun onStart() {//자동 로그인 함수
        Log.d("daeho", "this is signINUser")
        super.onStart()
        val user = auth.currentUser
        if(user!=null){ //로그인 되어있으면 바로 메인 화면으로 이동
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
            Users.readInfo()
        }else{//로그인 되어있지 않으면
            Toast.makeText(this,"Do Your Best!", Toast.LENGTH_LONG).show()
        }
    }

    fun hideKeyboard(v: View){
        if(v!=null){
            imm?.hideSoftInputFromWindow(v.windowToken,0)
        }
    }

    //인터넷 연결 여부를 확인하는 함수
    fun checkInternetConnection() : Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null)
            return true

        return false
    }

    @Override
    override fun onBackPressed() {
        //뒤로가기 버튼 막기
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }



}