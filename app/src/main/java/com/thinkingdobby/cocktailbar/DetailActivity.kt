package com.thinkingdobby.cocktailbar

import android.graphics.*
import android.graphics.drawable.RotateDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.rotationMatrix
import androidx.exifinterface.media.ExifInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import com.thinkingdobby.cocktailbar.login.Admin
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    private var drinkDB : DrinkDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        val bundle = intent.extras
        val drink = bundle!!.getParcelable<Drink>("selectedDrink")!!

        val nowColor = when (drink.tasteType) {
            "1" -> "#FFCCCC"
            "2" -> "#DAE3F3"
            "3" -> "#FBE5D6"
            else -> "#AFABAB"
        }

        // statusBar
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor= Color.parseColor(nowColor)
        // statusBar

        detail_tv_drinkName.setBackgroundColor(Color.parseColor(nowColor))

        detail_tv_drinkName.text = drink.drinkName
        detail_tv_ingredient.text = drink.ingredient
        detail_tv_explain.text = drink.explain

        val options = BitmapFactory.Options()
        options.inSampleSize = 4
        var bitmap = BitmapFactory.decodeByteArray(drink.image, 0, drink.image!!.size, options)
        //detail_iv_drink.setImageBitmap(bitmap)

        fun imgRotate(bmp: Bitmap): Bitmap {
            val width = bmp.width
            val height = bmp.height

            val matrix = Matrix()
            matrix.postRotate((90).toFloat())

            val resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true)
            bmp.recycle()

            return resizedBitmap
        }

        bitmap = imgRotate(bitmap)

        Glide.with(applicationContext).asBitmap()
            .centerCrop()
            .load(bitmap)
            .into(detail_iv_drink)

        detail_iv_drink.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);


        drinkDB = DrinkDB.getInstance(this)

        if (Admin().getAdminValue()) {
            detail_btn_remove.visibility = View.VISIBLE
            if (drink.recipe != "") {
                detail_btn_recipe.visibility = View.VISIBLE
            }
        }

        detail_btn_remove.setOnClickListener {
            val builder = AlertDialog.Builder(this@DetailActivity)
            builder.setTitle("삭제하시겠습니까?")

            builder.setPositiveButton("아니오") { _, which ->
            }

            builder.setNegativeButton("예") {_, which ->
                // Room Delete
                GlobalScope.launch {
                    drinkDB?.drinkDao()?.delete(drink)
                    finish()
                }
                // Room Delete
            }
            builder.create().show()
        }

        detail_btn_recipe.setOnClickListener {
            val builder = AlertDialog.Builder(this@DetailActivity)
            builder.setTitle("${drink.drinkName} 조주법")
            builder.setMessage("${drink.recipe}")
            builder.setPositiveButton("확인") { _, which ->
            }

            builder.create().show()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
