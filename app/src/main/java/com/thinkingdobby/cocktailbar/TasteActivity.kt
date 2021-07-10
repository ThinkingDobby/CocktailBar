package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_taste.*

class TasteActivity : AppCompatActivity() {

    private val tasteTypes = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taste)

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