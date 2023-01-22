package com.example.numberproject.data

import androidx.room.*

@Dao
interface NumberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(number: Number)

    @Query("SELECT number, fact from number ORDER BY id DESC")
    fun getNumbers()

}
