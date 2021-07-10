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
        if (admin) {
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
        setContentView(R.layout.activity_list)

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = list_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar

        val tasteType = intent.getStringExtra("tasteType")!!
        drinkDB = DrinkDB.getInstance(this)

        var drinkAdapter: DrinkAdapter? = null
        list_rv.layoutManager = LinearLayoutManager(this@ListActivity)
        list_rv.setHasFixedSize(true)

        drinkDB?.drinkDao()?.getByTasteType(tasteType)!!.observe(this, androidx.lifecycle.Observer {
            // getAll() 에서 LiveData 로 데이터 기져옴 -> Observer 로 변화 감지 가능
            drinkAdapter = DrinkAdapter(drinkDB!!, it, this@ListActivity)
            list_rv.adapter = drinkAdapter
        })
    }
}
