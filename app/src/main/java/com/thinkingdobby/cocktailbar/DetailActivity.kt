package com.thinkingdobby.cocktailbar

import android.graphics.*
import android.graphics.drawable.RotateDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
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

    /*
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
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        /*
        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = add_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar
         */

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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
