package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.thinkingdobby.cocktailbar.data.MyApplication
import com.thinkingdobby.cocktailbar.login.Admin
import kotlinx.android.synthetic.main.activity_main.*
import java.security.DigestException
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        main_btn_enter.setOnClickListener {
            main_et_pwInput.setText("")

            val intent = Intent(this, TasteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        main_btn_login.setOnClickListener {
            if (!Admin().getAdminValue()) {
                val passwordExist = MyApplication.prefs.getString("password") != "Not Set"
                main_btn_login.visibility = View.INVISIBLE
                main_et_pwInput.visibility = View.VISIBLE
                main_btn_pwSubmit.visibility = View.VISIBLE
                if (passwordExist) {
                    main_et_pwInput.hint = "비밀번호를 입력하세요"
                } else {
                    main_et_pwInput.hint = "새 비밀번호를 입력하세요"
                }
            } else {
                Admin().unsetAdminValue()
                main_btn_login.setBackgroundResource(R.drawable.lock_closed)
            }
        }

        main_btn_pwSubmit.setOnClickListener {
            val passwordExist = MyApplication.prefs.getString("password") != "Not Set"
            if (passwordExist) {
                val success = Admin().login(main_et_pwInput.text.toString())
                if (success) {
                    main_et_pwInput.visibility = View.INVISIBLE
                    main_btn_pwSubmit.visibility = View.INVISIBLE
                    main_btn_login.setBackgroundResource(R.drawable.lock_opened)
                    main_btn_login.visibility = View.VISIBLE
                    hideKeyboard(main_cl)
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            } else {
                Admin().setPassword(main_et_pwInput.text.toString())
                main_et_pwInput.setText("")
                main_et_pwInput.hint = "비밀번호를 입력하세요"
                Toast.makeText(this, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show()

                hideKeyboard(main_cl)
                main_et_pwInput.visibility = View.INVISIBLE
                main_btn_pwSubmit.visibility = View.INVISIBLE
                main_btn_login.visibility = View.VISIBLE
            }
        }
    }

    fun hideKeyboard(v: View) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
