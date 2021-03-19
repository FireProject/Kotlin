package com.example.BurningUp

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

//ref : https://magicalcode.tistory.com/19
/*
TODO :
1. 이 Dialog는 새로운 UI이므로 상의
2. xml을 dialog에 접목시키는 것은 성공 , 하지만 layout의 비율이 깨지며
method 기능도 추가해야 함.
 */

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