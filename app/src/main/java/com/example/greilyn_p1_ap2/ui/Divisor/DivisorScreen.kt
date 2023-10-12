package com.example.greilyn_p1_ap2.ui.Divisor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greilyn_p1_ap2.data.local.entity.Divisor

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DivisorScreen(viewModel: DivisorViewModel = hiltViewModel()){
    val divisores by viewModel.divisorob.collectAsState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        val keyBoardControlle = LocalSoftwareKeyboardController.current
        Text(text = "Aprende a Dividir")

        OutlinedTextField(value = viewModel.nombre, onValueChange = {
            
        }, modifier = Modifier.fillMaxSize(),
            label = { Text(text = "Nombre")})
        /*Row (
            OutlinedTextField(value = viewModel.dividendo.toString(), onValueChange = {
                val valor = it.toIntOrNull()
                if(valor != null){
                    viewModel.dividendo = valor }
            }, modifier = Modifier.fillMaxSize(),
                label = { Text(text = "Dividendo")},
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next
                )


        )*/
        OutlinedButton(onClick = {
            keyBoardControlle?.hide()
            if(viewModel.Validar()){
                viewModel.saveDivisor()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
            Text(text = "Guardar")
        }
        ConsultaDivisor(divisor = divisores)
    }
}

@Composable
fun ConsultaDivisor(divisor: List<Divisor>){
    Text(text = "Lista Divisores", style = MaterialTheme.typography.titleMedium)
    LazyColumn( modifier = Modifier.fillMaxWidth()){
        items(divisor){
            divisor ->
            Text(text = divisor.nombre)
            Text(text = divisor.divisor.toString())
        }
    }

}


