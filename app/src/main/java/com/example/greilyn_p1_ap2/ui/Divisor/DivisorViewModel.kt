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
    var divisor by mutableStateOf(0)
    var cociente by mutableStateOf(0)
    var residuo by mutableStateOf(0)
    var dividendo by mutableStateOf(0)

    //validar si esta vacio o no
    var nombreInValido by mutableStateOf(true)
    var divisorInValido by mutableStateOf(true)
    var cocienteInValido by mutableStateOf(true)
    var residuoInValido by mutableStateOf(true)
    var dividendoInValido by mutableStateOf(true)


    var divisionValida by mutableStateOf(true)
    var cocienteValido by mutableStateOf(true)
    var residuoValido by mutableStateOf(true)

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
            if(validar()){
                divisorRepository.saveDivisor(divisorObj)
                clean()
            }
        }
    }

    fun deleteDivisor(divisor: Divisor) {
        viewModelScope.launch {
            divisorRepository.deletedDivisor(divisor)
        }
    }

    fun clean() {
        nombre = ""
        divisor = 0
        dividendo = 0
        cociente = 0
        residuo = 0
        nombreInValido = true
        divisorInValido = true
        cocienteInValido = true
        residuoInValido = true
        dividendoInValido = true
    }
    fun validar(): Boolean{
        if(!camposNoVaciosValidos()) {
            return false
        }

            divisionValida = dividendo == cociente * divisor + residuo // division valida

            cocienteValido = dividendo/divisor == cociente

            residuoValido = dividendo % divisor == residuo

            return divisionValida

    }
    private fun camposNoVaciosValidos(): Boolean{
        if(divisor <= 0){
            divisorInValido = false
            return false
        }else
            divisorInValido =true

        if(nombre.isBlank()) {
            nombreInValido = false
            return nombreInValido
        }
        else if(dividendo <= 0){
            dividendoInValido= false
            return dividendoInValido
        }
        else if(divisor <= 0){
            divisorInValido = false
            return dividendoInValido
        }
        else if(cociente <= 0){
            divisorInValido = false
            return cocienteInValido
        }
        else if(residuo < 0){
            divisorInValido = false
            return residuoInValido
        }
        else{
            return true
        }
    }
}