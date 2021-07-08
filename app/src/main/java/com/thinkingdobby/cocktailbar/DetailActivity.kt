package com.thinkingdobby.cocktailbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thinkingdobby.cocktailbar.data.Drink
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("drinkBundle")!!
        val drink = bundle.getParcelable<Drink>("selectedDrink")!!

        detail_tv_drinkName.text = drink.drinkName
        detail_tv_ingredient.text = drink.ingredient
        detail_tv_explain.text = drink.explain
    }
}
