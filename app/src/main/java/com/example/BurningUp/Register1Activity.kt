package com.example.BurningUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityRegister1Binding
import com.google.firebase.auth.FirebaseAuth

class Register1Activity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private var mBinding: ActivityRegister1Binding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.emailVerifyBtn.setOnClickListener{

            if(binding.passwordEdit.text.toString() != binding.passwordEditC.text.toString()){
                Log.d("jiwon" , "daeho1")
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
            }else if(binding.emailEdit.text.isNotEmpty()&&binding.passwordEdit.text.isNotEmpty()&&binding.passwordEditC.text.isNotEmpty()){
                sendVerifyEmail();
                Log.d("jiwon" , "daeho2")
            }else{
                Log.d("jiwon" , "daeho3")
                Toast.makeText(this, "이메일/비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            }

        }


    }

    private fun sendVerifyEmail(){

        Log.d("jiwon" , binding.emailEdit.text.trim().toString() + " " + binding.passwordEdit.text.trim().toString())
        auth.createUserWithEmailAndPassword(binding.emailEdit.text.trim().toString(), binding.passwordEdit.text.trim().toString())
            .addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    //이메일 인증 보내기
                    val user = auth.currentUser;
                    Log.d("jiwon" , "2")
                    user!!.sendEmailVerification()
                            .addOnCompleteListener{task->
                                if(task.isSuccessful){
                                    Toast.makeText(this, "이메일 인증이 발송되었습니다.", Toast.LENGTH_LONG).show()
                                    //auth.signOut();
                                    val intent = Intent(this, Register2Activity::class.java);
                                    startActivity(intent)
                                    Log.d("jiwon" , "3")
                                }else{
                                    Toast.makeText(this,"이메일이 전송되지 않았습니다 "+ task.exception,Toast.LENGTH_LONG).show()
                                    Log.d("jiwon" , "4")
                                }
                            }
                }else{
                    Log.d("jiwon" , "1")
                    Toast.makeText(this, "회원 가입 오류 " + task.exception, Toast.LENGTH_LONG).show()

                }
            }
    }
    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

}