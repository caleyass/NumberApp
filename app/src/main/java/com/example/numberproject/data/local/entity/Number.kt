package com.example.numberproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "number")
data class Number (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val number : Long,
    val fact : String?,
)