package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.thinkingdobby.cocktailbar.data.MyApplication
import com.thinkingdobby.cocktailbar.login.Admin
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
            val passwordExist = MyApplication.prefs.getString("password") != "Not Set"
            main_btn_login.visibility = View.INVISIBLE
            main_et_pwInput.visibility = View.VISIBLE
            main_btn_pwSubmit.visibility = View.VISIBLE
            if (passwordExist) {
                main_et_pwInput.hint = "비밀번호를 입력하세요"
            } else {
                main_et_pwInput.hint = "새 비밀번호를 입력하세요"
            }
        }

        main_btn_pwSubmit.setOnClickListener {
            val passwordExist = MyApplication.prefs.getString("password") != "Not Set"
            if (passwordExist) {
                val success = Admin().login(main_et_pwInput.text.toString())
                if (success) {
                    main_et_pwInput.visibility = View.INVISIBLE
                    main_btn_pwSubmit.visibility = View.INVISIBLE
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            } else {
                Admin().setPassword(main_et_pwInput.text.toString())
                main_et_pwInput.setText("")
                main_et_pwInput.hint = "비밀번호를 입력하세요"
                Toast.makeText(this, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show()

                main_et_pwInput.visibility = View.INVISIBLE
                main_btn_pwSubmit.visibility = View.INVISIBLE
                main_btn_login.visibility = View.VISIBLE
            }
        }
    }
}
