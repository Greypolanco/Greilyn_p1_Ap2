package com.example.greilyn_p1_ap2.data.repository

import com.example.greilyn_p1_ap2.data.local.dao.DivisorDao
import com.example.greilyn_p1_ap2.data.local.entity.Divisor
import javax.inject.Inject

class DivisorRepository @Inject constructor(
    private val divisorDao: DivisorDao
) {
    suspend fun saveDivisor(divisor: Divisor) = divisorDao.save(divisor)
    suspend fun deletedDivisor(divisor: Divisor) = divisorDao.deleted(divisor)
    suspend fun  findDivisor(id : Int) = divisorDao.find(id)
    fun getALlDivisor() = divisorDao.getAll()
}