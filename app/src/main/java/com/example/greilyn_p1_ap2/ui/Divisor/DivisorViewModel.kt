package com.example.greilyn_p1_ap2.ui.Divisor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greilyn_p1_ap2.data.local.entity.Divisor
import com.example.greilyn_p1_ap2.data.repository.DivisorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DivisorViewModel @Inject constructor(
    private val divisorRepository: DivisorRepository
): ViewModel() {

    var nombre by mutableStateOf("")
    val divisor by mutableStateOf(0)
    val cociente by mutableStateOf(0)
    val residuo by mutableStateOf(0)
    var dividendo by mutableStateOf(0)

    val divisorob: StateFlow<List<Divisor>> = divisorRepository.getALlDivisor().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun saveDivisor(){
        viewModelScope.launch {
            val divisorObj = Divisor(
                nombre = nombre,
                divisor = divisor,
                cociente = cociente,
                residuo = residuo,
                dividendo = dividendo
            )
            if(Validar()){
                divisorRepository.saveDivisor(divisorObj)
                clean()
            }
        }
    }

    fun clean() {
        nombre = ""


    }
    fun Validar(): Boolean{
        return !(nombre.isBlank() ||
                divisor.equals(0) ||
                cociente.equals(0)||
                residuo.equals(0) ||
                dividendo.equals(0))
    }

}