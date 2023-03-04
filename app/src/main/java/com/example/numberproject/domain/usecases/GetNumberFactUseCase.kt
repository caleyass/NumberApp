package com.example.numberproject.domain.usecases

import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import retrofit2.Call
import java.math.BigInteger

class GetNumberFactUseCase(private val numberRepository: NumberRepository)  {

    fun execute (number: BigInteger?): Call<String> {
        if(number != null)
            return numberRepository.getFact(number)
        return numberRepository.getRandomFact()
    }
}