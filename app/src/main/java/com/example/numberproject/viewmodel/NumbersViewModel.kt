package com.example.numberproject.viewmodel

import androidx.lifecycle.*
import com.example.numberproject.data.Number
import com.example.numberproject.data.NumberDao
import com.example.numberproject.network.NumberApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.IllegalArgumentException
import java.math.BigInteger
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NumbersViewModel(private val numberDao : NumberDao) : ViewModel() {

    /*private var _number = MutableLiveData<Number>()
    val number : LiveData<Number>
        get() = _number*/
    lateinit var fact:String

    val allNumbers : LiveData<List<Number>> = numberDao.getNumbers().asLiveData()

    fun addNumber(number: Number){
        insertNumber( number)
    }

    /**
     * @param number entered number
     * Get fact about number
     * */
    fun getFact(number : BigInteger?) : String
    {
        val exec : ExecutorService = Executors.newSingleThreadExecutor()
        exec.execute{
            fact = if(number!=null)
                NumberApi.retrofitService.getFact(number).execute().body()!!
            else
                NumberApi.retrofitService.getRandomFact().execute().body()!!
        }
        exec.shutdown()
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
        return fact
    }

    private fun insertNumber(number: Number){
        viewModelScope.launch {
            numberDao.insert(number)
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