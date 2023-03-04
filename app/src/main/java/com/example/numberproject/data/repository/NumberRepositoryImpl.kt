package com.example.numberproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.numberproject.data.local.NumberDao
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.math.BigInteger

class NumberRepositoryImpl(private val api:NumberApiService, private val dao : NumberDao) :
    NumberRepository {
    override fun getFact(number: BigInteger?): Call<String> {
        if(number == null)
            return api.getRandomFact()
        return api.getFact(number)
    }

    override fun getNumbers(): LiveData<List<Number>> {
        return dao.getNumbers().asLiveData()
    }

    override suspend fun insertNumber(number: Number) {
        return dao.insert(number)
    }

}