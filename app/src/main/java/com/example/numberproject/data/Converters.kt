package com.example.numberproject.data

import androidx.room.TypeConverter
import java.math.BigInteger

class Converters {
    @TypeConverter
    fun fromBigint(value: Long?): BigInteger? {
        return value?.toBigInteger()
    }

    @TypeConverter
    fun bigintToLong(value : BigInteger?): Long? {
        return value?.toLong()
    }
}