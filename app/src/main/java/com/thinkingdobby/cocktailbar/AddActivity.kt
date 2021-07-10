package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    var tasteTypes = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6"
    )  // 변경 필요

    private var selectedTasteType = tasteTypes[0]

    // toolBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_btn_save -> {
                // Room Add
                GlobalScope.launch {
                    drinkDB = DrinkDB.getInstance(this@AddActivity)

                    val newDrink = Drink()
                    newDrink.drinkName = add_et_name.text.toString()
                    newDrink.ingredient = add_et_ingredient.text.toString()
                    newDrink.tasteType = selectedTasteType
                    newDrink.explain = add_et_explain.text.toString()
                    drinkDB?.drinkDao()?.insert(newDrink)
                }
                // Room Add

                val intent = Intent(this, TasteActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    // toolBar

    private var drinkDB : DrinkDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = add_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar

        // Spinner
        add_sp_tasteType.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            tasteTypes
        )

        add_sp_tasteType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedTasteType = tasteTypes[position]
            }
        }
        // Spinner
    }

    override fun onDestroy() {
        DrinkDB.destroyInstance()
        super.onDestroy()
    }
}
