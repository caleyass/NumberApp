package com.example.numberproject.data.repository

import com.example.numberproject.data.local.NumberDao
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.math.BigInteger

class NumberRepositoryImpl(private val api:NumberApiService, private val dao : NumberDao) :
    NumberRepository {
    override fun getFact(number: Long): Call<String> {
        return api.getFact(number)
    }

    override fun getRandomFact(): Call<String> {
        return api.getRandomFact()
    }

    override fun getNumbers(): Flow<List<Number>> {
        return dao.getNumbers()
    }

    override suspend fun insertNumber(number: Number) {
        return dao.insert(number)
    }

}