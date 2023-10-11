package com.example.greilyn_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Divisor")
data class Divisor(
    @PrimaryKey(autoGenerate = true)

    val divisorId : Int?=null,
    var nombre : String = "",
    val dividendo : Int?=null,
    val divisor : Int?=null,
    val cociente : Int?= null,
    val residuo : Int? = null
)
