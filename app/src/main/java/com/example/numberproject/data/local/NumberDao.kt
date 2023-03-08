package com.example.numberproject.data.local

import androidx.room.*
import com.example.numberproject.data.local.entity.Number
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(number: Number)

    @Query("SELECT id, number, fact from number ORDER BY id DESC")
    fun getNumbers() : Flow<List<Number>>
}