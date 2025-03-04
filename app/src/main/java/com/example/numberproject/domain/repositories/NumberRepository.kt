package com.example.numberproject.domain.repositories

import com.example.numberproject.data.local.entity.Number
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.math.BigInteger

interface NumberRepository {
    suspend fun getFact(number: Long): String?
    suspend fun getRandomFact(): String?
    fun getNumbers() : Flow<List<Number>>
    suspend fun insertNumber(number: Number)
}