package com.example.numberproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.math.BigInteger

@Entity(tableName = "number")
data class Number (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val number : BigInteger,
    val fact : String?,
)