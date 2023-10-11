package com.example.greilyn_p1_ap2.ui.Divisor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greilyn_p1_ap2.data.local.entity.Divisor
import com.example.greilyn_p1_ap2.data.repository.DivisorRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DivisorViewModel @Inject constructor(
    private val divisorRepository: DivisorRepository
): ViewModel() {

    var nombre by mutableStateOf("")
    val divisor by mutableStateOf(0)
    val conciente by mutableStateOf(0)
    val residuo by mutableStateOf(0)
    val dividendo by mutableStateOf(0)

    fun saveDivisor(){
        viewModelScope.launch {
            divisorRepository.saveDivisor(

            )
        }
    }

}