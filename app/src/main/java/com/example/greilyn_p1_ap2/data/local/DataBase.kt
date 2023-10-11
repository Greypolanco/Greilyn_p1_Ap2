package com.example.greilyn_p1_ap2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.greilyn_p1_ap2.data.local.dao.DivisorDao
import com.example.greilyn_p1_ap2.data.local.entity.Divisor

@Database(
    entities = [Divisor::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase: RoomDatabase() {
    abstract fun divisorDao() : DivisorDao
}