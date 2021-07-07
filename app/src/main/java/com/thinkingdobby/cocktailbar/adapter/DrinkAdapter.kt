package com.thinkingdobby.cocktailbar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thinkingdobby.cocktailbar.R
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import com.thinkingdobby.cocktailbar.viewHolder.DrinkViewHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DrinkAdapter(val db: DrinkDB, val drinks: List<Drink>)
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
        holder.bind(drinks[position])
        holder.list_rv_btn_remove.setOnClickListener {
            GlobalScope.launch {
                db.drinkDao().delete(drinks[position])
            }
        }
    }
}