package com.thinkingdobby.cocktailbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkingdobby.cocktailbar.adapter.DrinkAdapter
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private var drinkDB : DrinkDB? = null
    private var drinkList = listOf<Drink>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        drinkDB = DrinkDB.getInstance(this)

        val r = Runnable {
            drinkList = drinkDB?.drinkDao()?.getAll()!!.sortedBy { it.drinkName }

            val drinkAdapter = DrinkAdapter(this, drinkList)
            drinkAdapter.notifyDataSetChanged()
            list_rv.adapter = drinkAdapter
            list_rv.layoutManager = LinearLayoutManager(this)
            list_rv.setHasFixedSize(true)
        }

        val thread = Thread(r)
        thread.start()
    }
}
