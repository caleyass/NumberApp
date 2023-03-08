package com.example.numberproject.domain.usecases

import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import retrofit2.Call
import java.math.BigInteger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GetNumberFactUseCase(private val numberRepository: NumberRepository)  {

    fun execute (number: Long?): String {
        var fact:String = ""
        val exec : ExecutorService = Executors.newSingleThreadExecutor() // create new thread to not run in main thread
        exec.execute{
            fact = if(number != null)
                numberRepository.getFact(number).execute().body()!!
            else numberRepository.getRandomFact().execute().body()!!
        }
        exec.shutdown() // wait until code is executed because fact may be null
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
        return fact
    }
}