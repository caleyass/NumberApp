package com.example.numberproject.domain.usecases

import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.domain.repositories.NumberRepository

class InsertNumberUseCase(private val numberRepository: NumberRepository) {
    suspend fun insert(number: Number){
        return numberRepository.insertNumber(number)
    }
}