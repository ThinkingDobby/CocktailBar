package com.thinkingdobby.cocktailbar

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.thinkingdobby.cocktailbar.data.Drink
import com.thinkingdobby.cocktailbar.data.DrinkDB
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class AddActivity : AppCompatActivity() {

    private var tasteTypes = arrayOf(
        "달콤하고 부드러운 맛",
        "달콤하지만 높은 도수",
        "깔끔하고 높은 도수",
        "그 외의 음료"
    )  // 변경 필요

    private var imm: InputMethodManager? = null

    private var selectedTasteType = "1"

    private val PICK_IMAGE = 0
    private var uriPhoto : Uri? = Uri.parse("android.resource://com.thinkingdobby.cocktailbar/drawable/default_image")

    // toolBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_btn_save -> {
                // Room Add
                GlobalScope.launch {
                    drinkDB = DrinkDB.getInstance(this@AddActivity)

                    val newDrink = Drink()
                    newDrink.drinkName = add_et_name.text.toString()
                    newDrink.ingredient = add_et_ingredient.text.toString()
                    newDrink.tasteType = selectedTasteType
                    newDrink.explain = add_et_explain.text.toString()
                    newDrink.image = getByteArrayFromDrawable(uriPhoto!!)
                    drinkDB?.drinkDao()?.insert(newDrink)
                }
                // Room Add

                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    // toolBar

    private var drinkDB : DrinkDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // request permission
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        // request permission

        // statusBar
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor= Color.parseColor("#FFFFFF")

        @RequiresApi(Build.VERSION_CODES.M)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // statusBar

        // toolBar
        val toolBar: androidx.appcompat.widget.Toolbar? = add_tb
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // toolBar

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        // Spinner
        add_sp_tasteType.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item,
            tasteTypes
        )

        add_sp_tasteType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedTasteType = when (tasteTypes[position]) {
                    "달콤하고 부드러운 맛" -> "1"
                    "달콤하지만 높은 도수" -> "2"
                    "깔끔하고 높은 도수" -> "3"
                    else -> "4"
                }
            }
        }
        // Spinner

        add_btn_pickImage.setOnClickListener {
            pickImage()
        }
    }

    fun hideKeyboard(v: View) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), PICK_IMAGE)
    }

    private fun getByteArrayFromDrawable(uri: Uri): ByteArray {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream)

        return stream.toByteArray()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uriPhoto = data?.data
                add_iv_drink.setImageURI(uriPhoto)
            } else {
                finish()
            }
        }
    }

    override fun onDestroy() {
        DrinkDB.destroyInstance()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
