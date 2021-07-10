package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.security.DigestException
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btn_enter.setOnClickListener {
            val intent = Intent(this, TasteActivity::class.java)
            startActivity(intent)
        }

        main_btn_login.setOnClickListener {
            // 관리자 등록이 되어있지 않으면 -> 새 패스워드 입력 -> 저장
            // 관리자 등록이 되어있는 경우 -> 패스워드 입력 -> 대조 -> 일치 시 로그인
        }
    }
}
