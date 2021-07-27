package com.thinkingdobby.cocktailbar.viewHolder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thinkingdobby.cocktailbar.data.Drink
import kotlinx.android.synthetic.main.drink_card.view.*

class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val list_rv_tv_name = itemView.list_rv_tv_name
    val list_rv_img = itemView.list_rv_img

    fun bind(drink: Drink, bitmap: Bitmap) {
        list_rv_tv_name.text = drink.drinkName


        fun imgRotate(bmp: Bitmap): Bitmap {
            val width = bmp.width
            val height = bmp.height

            val matrix = Matrix()
            matrix.postRotate((90).toFloat())

            val resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true)
            bmp.recycle()

            return resizedBitmap
        }

        list_rv_img.setImageBitmap(imgRotate(bitmap))
    }
}