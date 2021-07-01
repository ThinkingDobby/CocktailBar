package com.thinkingdobby.cocktailbar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Drink::class], version = 1)
abstract class DrinkDB : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: DrinkDB? = null

        fun getInstance(context: Context): DrinkDB? {
            if (INSTANCE == null) {
                synchronized(DrinkDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DrinkDB::class.java, "Drink.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}