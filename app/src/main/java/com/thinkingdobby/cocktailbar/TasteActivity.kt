package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.thinkingdobby.cocktailbar.login.Admin
import kotlinx.android.synthetic.main.activity_taste.*
import kotlinx.android.synthetic.main.activity_taste.list_tb

class TasteActivity : AppCompatActivity() {

    private val tasteTypes = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6"
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

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = list_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar

        val intent = Intent(this, ListActivity::class.java)
        fun sendIntent(type: String) {
            intent.putExtra("tasteType", type)
            startActivity(intent)
        }

        taste_cv_1.setOnClickListener { sendIntent(tasteTypes[0]) }
        taste_cv_2.setOnClickListener { sendIntent(tasteTypes[1]) }
        taste_cv_3.setOnClickListener { sendIntent(tasteTypes[2]) }
        taste_cv_4.setOnClickListener { sendIntent(tasteTypes[3]) }
        taste_cv_5.setOnClickListener { sendIntent(tasteTypes[4]) }
        taste_cv_6.setOnClickListener { sendIntent(tasteTypes[5]) }
    }
}