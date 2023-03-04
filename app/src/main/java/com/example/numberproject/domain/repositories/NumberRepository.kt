package com.example.numberproject.domain.repositories

import androidx.lifecycle.LiveData
import com.example.numberproject.data.local.entity.Number
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.math.BigInteger

interface NumberRepository {
    fun getFact(number: BigInteger?): Call<String>
    fun getNumbers() : LiveData<List<Number>>
    suspend fun insertNumber(number: Number)
}