package com.example.numberproject.domain.usecases

import java.math.BigInteger
import com.example.numberproject.data.local.entity.Number

class CreateNumberUseCase {
    fun execute(num : Long?, fact:String) : Number{
        if(num == null) // in case user wanted to get random number
        // 1st word of the fact contains number that is returned
            return Number(0,number = fact.split(" ")[0].toLong(), fact = fact)
        return Number(0, number = num, fact = fact) // in case user wanted to get random number
    }
}