package com.example.numberproject.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(number: Number)

    @Query("SELECT id, number, fact from number ORDER BY id DESC")
    fun getNumbers() : Flow<List<Number>>
}

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