package com.example.numberproject.data.local

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