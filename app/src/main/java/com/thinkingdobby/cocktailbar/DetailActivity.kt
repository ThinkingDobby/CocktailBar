package com.thinkingdobby.cocktailbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import com.thinkingdobby.cocktailbar.login.Admin
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.add_tb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private var drinkDB : DrinkDB? = null

    // toolBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_btn_return -> {
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
        setContentView(R.layout.activity_detail)

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = add_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar

        val bundle = intent.getBundleExtra("drinkBundle")!!
        val drink = bundle.getParcelable<Drink>("selectedDrink")!!

        detail_tv_drinkName.text = drink.drinkName
        detail_tv_ingredient.text = drink.ingredient
        detail_tv_explain.text = drink.explain

        drinkDB = DrinkDB.getInstance(this)

        if (Admin().getAdminValue()) {
            detail_rv_btn_remove.visibility = View.VISIBLE
        }

        detail_rv_btn_remove.setOnClickListener {
            // Room Delete
            GlobalScope.launch {
                drinkDB?.drinkDao()?.delete(drink)
                finish()
            }
            // Room Delete
        }
    }
}
