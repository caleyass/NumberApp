package com.example.numberproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.numberproject.data.Number
import com.example.numberproject.data.NumberDao
import kotlinx.coroutines.launch

class NumbersViewModel(private val numberDao : NumberDao) : ViewModel() {
    val allNumbers : LiveData<List<Number>> = numberDao.getNumbers().asLiveData()

    fun addNumber(number : Double, fact:String){
        insertNumber(Number(number = number, fact = fact))
    }

    private fun insertNumber(number: Number){
        viewModelScope.launch {
            numberDao.insert(number)
        }
    }
}