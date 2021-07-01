package com.thinkingdobby.cocktailbar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Drink")
class Drink(@PrimaryKey var id: Long?,
            @ColumnInfo(name = "drinkName") var drinkName: String?,
            @ColumnInfo(name = "ingredient") var ingredient: String?,
            @ColumnInfo(name = "tasteType") var tasteType: String?,
            @ColumnInfo(name = "explain") var explain: String?) {
    constructor(): this(null, "", "", "", "")
}