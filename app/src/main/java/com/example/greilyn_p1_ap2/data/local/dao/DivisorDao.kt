package com.example.greilyn_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.greilyn_p1_ap2.data.local.entity.Divisor
import kotlinx.coroutines.flow.Flow

@Dao
interface DivisorDao {
    @Upsert
    suspend fun save(divisor: Divisor)

    @Query("SELECT * FROM Divisor WHERE divisorId= :id")
    suspend fun find(id : Int): Divisor?

    @Query("SELECT * FROM Divisor ORDER BY nombre")
    fun getAll() : Flow<List<Divisor>>
}