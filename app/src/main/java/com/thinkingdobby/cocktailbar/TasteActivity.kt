package com.thinkingdobby.cocktailbar

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import com.thinkingdobby.cocktailbar.login.Admin
import kotlinx.android.synthetic.main.activity_taste.*
import kotlinx.android.synthetic.main.activity_taste.list_tb

class TasteActivity : AppCompatActivity() {

    private val tasteTypes = arrayOf(
        "1",
        "2",
        "3",
        "4"
    )

    // toolBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        if (Admin().getAdminValue()) {
            menuInflater.inflate(R.menu.menu_toadd, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_btn_add -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    // toolBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taste)

        // statusBar
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor= Color.parseColor("#FFFFFF")

        @RequiresApi(Build.VERSION_CODES.M)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // statusBar

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = list_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        list_tb.bringToFront()
        // toolBar

        val intent = Intent(this, ListActivity::class.java)
        fun sendIntent(type: String) {
            intent.putExtra("tasteType", type)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        taste_v_1.setOnClickListener { sendIntent(tasteTypes[0]) }
        taste_v_2.setOnClickListener { sendIntent(tasteTypes[1]) }
        taste_v_3.setOnClickListener { sendIntent(tasteTypes[2]) }
        taste_v_4.setOnClickListener { sendIntent(tasteTypes[3]) }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}