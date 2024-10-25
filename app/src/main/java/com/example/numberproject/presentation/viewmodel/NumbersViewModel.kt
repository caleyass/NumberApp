package com.example.numberproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.data.local.NumberDao
import com.example.numberproject.data.remote.NumberApi
import com.example.numberproject.data.repository.NumberRepositoryImpl
import com.example.numberproject.domain.usecases.CreateNumberUseCase
import com.example.numberproject.domain.usecases.GetAllNumbersUseCase
import com.example.numberproject.domain.usecases.GetNumberFactUseCase
import com.example.numberproject.domain.usecases.InsertNumberUseCase
import kotlinx.coroutines.async
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
    private val createNumberUseCase : CreateNumberUseCase by lazy { CreateNumberUseCase() }
    val allNumbers : LiveData<List<Number>> = getAllNumberUseCase.execute()

    private val _fact = MutableLiveData<String>()
    val fact: LiveData<String> get() = _fact

    private val _number = MutableLiveData<Number>()
    val number: LiveData<Number> get() = _number

    /**
     * @param num BigInteger number
     * @return Number object with entered number and fact about it
     * */
    fun createNumber(num: Long?) {

        val number : Long = num ?: (1..100).random().toLong()
        viewModelScope.launch {
            Log.d("TAG", "createNumber started")
            // Fetch the fact asynchronously
            val fact = async { getNumberFactUseCase.execute(number) }.await()
            Log.d("TAG", "createNumber fact: $fact")
            // Use the fact to create the Number object
            val numberObject = createNumberUseCase.execute(number, fact)
            addNumber(numberObject)

            _number.value = numberObject
            // Update LiveData or perform other actions with the created Number object
            _fact.value = fact
        }


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