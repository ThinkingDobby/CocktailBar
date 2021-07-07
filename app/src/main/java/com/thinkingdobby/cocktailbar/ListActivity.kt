package com.thinkingdobby.cocktailbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkingdobby.cocktailbar.adapter.DrinkAdapter
import com.thinkingdobby.cocktailbar.data.DrinkDB
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private var drinkDB : DrinkDB? = null

    // toolBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu., menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> {
                val intent = Intent(this, AddActivity::class.java)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        drinkDB = DrinkDB.getInstance(this)

        var drinkAdapter: DrinkAdapter? = null

        list_rv.layoutManager = LinearLayoutManager(this@ListActivity)
        list_rv.setHasFixedSize(true)

        drinkDB?.drinkDao()?.getAll()!!.observe(this, androidx.lifecycle.Observer {
            drinkAdapter = DrinkAdapter(drinkDB!!, it)
            list_rv.adapter = drinkAdapter
        })
    }
}
