package com.example.numberproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class Number (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val number : Double,
    val fact : String,
    )