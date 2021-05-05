package com.example.BurningUp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test_recycle.*

class TestRecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycle)

        val chatroom_list = arrayListOf(
                RecycleChatroom("a","b"),
                RecycleChatroom("c","d"),
                RecycleChatroom("e","f")
        )

        rv_test.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_test.setHasFixedSize(true);
        rv_test.adapter = RecycleChatroomAdapter(chatroom_list);

    }
}