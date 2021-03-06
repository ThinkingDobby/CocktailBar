package com.thinkingdobby.cocktailbar.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DrinkDao {
    @Query("SELECT * FROM Drink ORDER BY drinkName")
    fun getAll(): LiveData<List<Drink>> // LiveData -> Observer 로 변화 감지 가능

    @Query("SELECT * FROM Drink WHERE tasteType = :tasteType")
    fun getByTasteType(tasteType: String): LiveData<List<Drink>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Delete
    fun delete(drink: Drink)
}