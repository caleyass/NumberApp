package com.example.numberproject

import android.app.Application
import com.example.numberproject.data.NumberDatabase

class NumberApplication : Application() {
    val database : NumberDatabase by lazy { NumberDatabase.getDatabase(this) }
}