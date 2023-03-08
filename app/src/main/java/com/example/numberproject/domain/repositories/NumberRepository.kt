package com.example.numberproject.domain.repositories

import com.example.numberproject.data.local.entity.Number
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.math.BigInteger

interface NumberRepository {
    fun getFact(number: Long): Call<String>
    fun getRandomFact():Call<String>
    fun getNumbers() : Flow<List<Number>>
    suspend fun insertNumber(number: Number)
}