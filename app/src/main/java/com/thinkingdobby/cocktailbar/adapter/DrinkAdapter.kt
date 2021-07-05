package com.thinkingdobby.cocktailbar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thinkingdobby.cocktailbar.R
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.viewHolder.DrinkViewHolder

class DrinkAdapter(val context: Context, val drinks: List<Drink>)
    : RecyclerView.Adapter<DrinkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.drink_card, parent, false)
        return DrinkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinks[position])
    }
}