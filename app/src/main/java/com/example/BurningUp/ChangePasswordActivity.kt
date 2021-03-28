package com.example.BurningUp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.BurningUp.databinding.ActivityChangePasswordBinding
import com.example.BurningUp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private var mBinding: ActivityChangePasswordBinding? = null
    private val binding get() = mBinding!!

    private lateinit var auth : FirebaseAuth

    var imm : InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mBinding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        binding.sendChangePasswordBtn.setOnClickListener {
            if(binding.emailForVerifyEdit.text.isNotEmpty()) {
                auth.sendPasswordResetEmail(binding.emailForVerifyEdit.text.toString())
                    .addOnCompleteListener(this) {
                            task ->
                            if (task.isSuccessful) {
                                val msg = "이메일 전송 완료. 이메일을 확인해주세요"
                                binding.emailSendStatusTv.setTextColor(Color.parseColor("#03A9F4"))
                                binding.emailSendStatusTv.text="$msg"
                                binding.sendChangePasswordBtn.setBackgroundResource(R.drawable.resend_change_password_email_btn)
                                binding.sendChangePasswordBtn.text="비밀번호 변경 이메일 재전송"
                            }
                            else {
                                Toast.makeText(
                                    this,
                                    "이메일이 전송되지 않았습니다 " + task.exception,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
            }
            else{
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            }
        }

        binding.changePasswordCompleteBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    fun hideKeyboard(v: View){
        if(v!=null){
            imm?.hideSoftInputFromWindow(v.windowToken,0)
        }
    }
}