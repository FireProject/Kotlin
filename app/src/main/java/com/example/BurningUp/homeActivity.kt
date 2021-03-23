package com.example.BurningUp

/**
 * 메인화면(홈 액티비티임 )
 */

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class homeActivity :AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //=버튼 눌렀을때 서랍 열기
        menubtn.setOnClickListener{
            layout_drawer.openDrawer(GravityCompat.START)//start는 left 와 같은 말 end=right
        }
        naviView.setNavigationItemSelectedListener(this)    //네비에기션 메뉴 각각의 아이템에 클릭 속성을 부여해준다.


    }
    //네비게이션 수행시 이거 -> 바꿔주기
    override fun onNavigationItemSelected(item: MenuItem): Boolean {    //네비게이션 메뉴 아이템 클릭시 수행
        when(item.itemId)
        {   //일단 버튼 눌렀을 때 토스트 메시지 띄워둠
            R.id.chprofile->Toast.makeText(applicationContext,"프로필 변경하기",Toast.LENGTH_SHORT).show()
            R.id.chpassword->Toast.makeText(applicationContext,"비밀번호 변경하기",Toast.LENGTH_SHORT).show()
            R.id.alarm->Toast.makeText(applicationContext,"알람설정",Toast.LENGTH_SHORT).show()
            R.id.introuduce_p->Toast.makeText(applicationContext,"개발자 소개 ",Toast.LENGTH_SHORT).show()

        }

        layout_drawer.closeDrawers()    //버튼이 입력되면 이 drawer를 닫아주기-> 네비게이션 뷰 닫기
        return false

    }


    override fun onBackPressed() {

        if(layout_drawer.isDrawerOpen(GravityCompat.START)) //네비게이션이 열려있으면 걔를 끄고
        {
            layout_drawer.closeDrawers()
        }
        else    //일반 백버튼 실행-> 어플이 꺼져버림
        {
            super.onBackPressed()
        }
    }
}