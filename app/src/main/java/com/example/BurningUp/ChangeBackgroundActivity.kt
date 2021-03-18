package com.example.BurningUp
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.BurningUp.databinding.ActivityChangeBackgroundBinding

class ChangeBackgroundActivity : AppCompatActivity()
{
    private var mBinding: ActivityChangeBackgroundBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChangeBackgroundBinding.inflate(layoutInflater)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)


    }
}