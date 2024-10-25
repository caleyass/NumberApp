package com.example.numberproject.domain.usecases

import android.util.Log
import com.example.numberproject.data.remote.NumberApiService
import com.example.numberproject.domain.repositories.NumberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.math.BigInteger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GetNumberFactUseCase(private val numberRepository: NumberRepository) {

    suspend fun execute(number: Long?): String {
        return try {
            // Call the appropriate repository method based on the presence of a number
            val fact = if (number != null) {
                numberRepository.getFact(number)
            } else {
                numberRepository.getRandomFact()
            }

            // Return the fact if available, or a default message if null
            fact ?: "No fact available for this number"
        } catch (e: Exception) {
            Log.e("TAG", "GetNumberFactUseCase: $e", )
            // Handle any exceptions during the API call
            "Error fetching fact"
        }
    }
}

