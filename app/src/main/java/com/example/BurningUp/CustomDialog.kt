package com.example.BurningUp

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

//ref : https://magicalcode.tistory.com/19
//TODO : 3/19에 위의 자료에서 4번 5번 부터 시작.
class CustomDialog(context : Context)
{
    private val dialog = Dialog(context)
    fun myDig()
    {
        dialog.setContentView(R.layout.activity_custom_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
    }
}