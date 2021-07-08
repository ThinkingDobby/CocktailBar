package com.thinkingdobby.cocktailbar.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thinkingdobby.cocktailbar.admin
import com.thinkingdobby.cocktailbar.data.Drink
import kotlinx.android.synthetic.main.drink_card.view.*

class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val list_rv_tv_name = itemView.list_rv_tv_name
    val list_rv_ingredient = itemView.list_rv_tv_ingredient
    val list_rv_btn_remove = itemView.list_rv_btn_remove
    val list_rv_img = itemView.list_rv_img

    fun bind(drink: Drink) {
        list_rv_tv_name.text = drink.drinkName
        list_rv_ingredient.text = drink.ingredient
        //list_rv_img

        activeRemoveBtn()
    }

    private fun activeRemoveBtn() {
        if (admin) {
            list_rv_btn_remove.visibility = View.VISIBLE
            list_rv_btn_remove.isClickable = true
        }
    }
}