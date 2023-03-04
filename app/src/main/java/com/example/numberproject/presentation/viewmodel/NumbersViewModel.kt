package com.example.numberproject.presentation.viewmodel

import androidx.lifecycle.*
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.data.local.NumberDao
import com.example.numberproject.data.remote.NumberApi
import com.example.numberproject.data.repository.NumberRepositoryImpl
import com.example.numberproject.domain.usecases.GetAllNumbersUseCase
import com.example.numberproject.domain.usecases.GetNumberFactUseCase
import com.example.numberproject.domain.usecases.InsertNumberUseCase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.math.BigInteger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NumbersViewModel(private val numberDao : NumberDao) : ViewModel() {

    private val numberRepositoryImpl : NumberRepositoryImpl by lazy { NumberRepositoryImpl(NumberApi.retrofitService, numberDao) }
    private val getNumberFactUseCase : GetNumberFactUseCase by lazy { GetNumberFactUseCase(numberRepositoryImpl)}
    private val insertNumberUseCase : InsertNumberUseCase by lazy { InsertNumberUseCase(numberRepositoryImpl)}
    private val getAllNumberUseCase : GetAllNumbersUseCase by lazy { GetAllNumbersUseCase(numberRepositoryImpl)}

    val allNumbers : LiveData<List<Number>> = getAllNumberUseCase.execute()


    /**
     * @param number entered number
     * @return fact about entered number
     * Get fact about number
     * */
    fun getFact(number : BigInteger?) : String
    {
        var fact:String = ""
        val exec : ExecutorService = Executors.newSingleThreadExecutor() // create new thread to not run in main thread
        exec.execute{
            fact = getNumberFactUseCase.execute(number)
        }
        exec.shutdown() // wait until code is executed because fact may be null
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
        return fact
    }
    /**
     * @param num BigInteger number
     * @return Number object with entered number and fact about it
     * */
    fun createNumber(num : BigInteger?) : Number {
        val fact = getFact(num) // get fact according to entered number
        if(num == null) // in case user wanted to get random number
            // 1st word of the fact contains number that is returned
             return Number(0,number = fact.split(" ")[0].toBigInteger(), fact = fact)
        return Number(0, number = num, fact = fact) // in case user wanted to get random number
    }

    /**
     * @param number
     * inserts number into database
     * */
    fun addNumber(number: Number){
        insertNumber( number)
    }

    /**
     * @param number
     * inserts number into database
     * */
    private fun insertNumber(number: Number){
        viewModelScope.launch {
            insertNumberUseCase.execute(number)
        }
    }
}

class NumbersViewModelFactory(private val numberDao : NumberDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NumbersViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NumbersViewModel(numberDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}