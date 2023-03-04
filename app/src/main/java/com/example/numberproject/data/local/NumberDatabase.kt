package com.example.numberproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.numberproject.data.local.entity.Number

@Database(entities = [Number::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun numberDao() : NumberDao

    companion object{
        @Volatile
        private var INSTANCE: NumberDatabase? = null

        fun getDatabase(context: Context): NumberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumberDatabase::class.java,
                    "number_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}