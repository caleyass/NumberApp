package com.example.numberproject.data.repository

import com.example.numberproject.data.local.NumberDao
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class NumberRepositoryImpl(private val api:NumberApiService, private val dao : NumberDao) :
    NumberRepository {

    override suspend fun getFact(number: Long): String? {
        val response = api.getFact(number)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun getRandomFact(): String? {
        val response = api.getRandomFact()
        return if (response.isSuccessful) response.body() else null
    }

    override fun getNumbers(): Flow<List<Number>> {
        return dao.getNumbers()
    }

    override suspend fun insertNumber(number: Number) {
        return dao.insert(number)
    }

}