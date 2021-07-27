package com.thinkingdobby.cocktailbar.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thinkingdobby.cocktailbar.DetailActivity
import com.thinkingdobby.cocktailbar.R
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import com.thinkingdobby.cocktailbar.viewHolder.DrinkViewHolder

class DrinkAdapter(val db: DrinkDB, val drinks: List<Drink>, val bitmapList: List<Bitmap>, val context: Context)
    : RecyclerView.Adapter<DrinkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.drink_card, parent, false)
        return DrinkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinks[position], bitmapList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("selectedDrink", drinks[position])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}