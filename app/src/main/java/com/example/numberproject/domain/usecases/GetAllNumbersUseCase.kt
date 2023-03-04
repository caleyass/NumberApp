package com.example.numberproject.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.domain.repositories.NumberRepository
import kotlinx.coroutines.flow.Flow

class GetAllNumbersUseCase(private val numberRepository: NumberRepository) {

    fun execute(): LiveData<List<Number>> {
        return numberRepository.getNumbers()
    }
}