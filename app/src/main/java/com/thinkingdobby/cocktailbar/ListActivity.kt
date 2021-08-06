package com.thinkingdobby.cocktailbar

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkingdobby.cocktailbar.adapter.DrinkAdapter
import com.thinkingdobby.cocktailbar.data.DrinkDB
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.drink_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {

    private var drinkDB : DrinkDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val tasteType = intent.getStringExtra("tasteType")!!
        drinkDB = DrinkDB.getInstance(this)

        val nowColor = when (tasteType) {
            "1" -> "#FFCCCC"
            "2" -> "#DAE3F3"
            "3" -> "#FBE5D6"
            else -> "#AFABAB"
        }

        // statusBar
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor= Color.parseColor(nowColor)
        // statusBar

        var drinkAdapter: DrinkAdapter? = null
        list_rv.layoutManager = LinearLayoutManager(this@ListActivity)
        list_rv.setHasFixedSize(true)

        drinkDB?.drinkDao()?.getByTasteType(tasteType)!!.observe(this, androidx.lifecycle.Observer {
            val sortedDrinkList = it.sortedBy { drink -> drink.drinkName }
            val bitmapList = mutableListOf<Bitmap>()
            for (i in sortedDrinkList) {
                val options = BitmapFactory.Options()
                options.inSampleSize = 16
                val bitmap = BitmapFactory.decodeByteArray(i.image, 0, i.image!!.size, options)
                bitmapList.add(bitmap)
            }
            // getAll() 에서 LiveData 로 데이터 기져옴 -> Observer 로 변화 감지 가능
            drinkAdapter = DrinkAdapter(drinkDB!!, sortedDrinkList, bitmapList, this@ListActivity)
            list_rv.adapter = drinkAdapter
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
