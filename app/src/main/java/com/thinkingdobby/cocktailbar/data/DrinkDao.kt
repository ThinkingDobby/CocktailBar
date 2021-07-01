package com.thinkingdobby.cocktailbar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrinkDao {
    @Query("SELECT * FROM Drink")
    fun getAll(): List<Drink>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Query("DELETE from Drink")
    fun deleteAll()
}