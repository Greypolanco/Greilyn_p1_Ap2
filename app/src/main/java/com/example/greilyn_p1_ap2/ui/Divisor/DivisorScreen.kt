package com.example.greilyn_p1_ap2.ui.Divisor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        //Nombre
        OutlinedTextField(value = viewModel.nombre, onValueChange = {
            viewModel.nombre = it },
            label = { Text(text = "Nombre")},
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        if(viewModel.nombreInValido == false){
            Text(text = "Nombre es Requerido", color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                //Dividendo
                OutlinedTextField(
                    value = viewModel.dividendo.toString(),onValueChange = {
                        val nuevo = it.toIntOrNull()
                        if (nuevo != null) {
                            viewModel.dividendo = nuevo
                        } },
                    label = { Text(text = "Dividendo")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
                if(viewModel.dividendoInValido == false){
                    Text(text = "Dividendo Requerido", color = Color.Red, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                //Divisor
                OutlinedTextField(
                    value = viewModel.divisor.toString(),
                    onValueChange = {
                        viewModel.divisor = it.toIntOrNull() ?: 0
                    },
                    label = { Text(text = "Divisor")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
                if(viewModel.divisorInValido == false){
                    Text(text = "Divisor Requerido", color = Color.Red, fontSize = 12.sp)
                }
            }

        }

        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ) {
                //Cociente
                OutlinedTextField(
                    value = viewModel.cociente.toString(), onValueChange = {
                        val nuevo = it.toIntOrNull()
                        if (nuevo != null) {
                            viewModel.cociente = nuevo
                        }
                    },
                    label = { Text(text = "Cociente") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
                if(viewModel.cocienteInValido == false){
                    Text(text = "Cociente Requerido", color = Color.Red, fontSize = 12.sp)
                }
                if(viewModel.cocienteValido == false){
                    Text(text = "Cociente Invalido", color = Color.Red, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)

            ){
                //Residuo
                OutlinedTextField(
                    value = viewModel.residuo.toString(),onValueChange = {
                        val nuevo = it.toIntOrNull()
                        if (nuevo != null) {
                            viewModel.residuo = nuevo
                        } },
                    label = { Text(text = "Residuo")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
                if(viewModel.residuoInValido == false){
                    Text(text = "Residuo Requerido", color = Color.Red, fontSize = 12.sp)
                }
                if(viewModel.residuoValido == false){
                    Text(text = "Residuo Invalido", color = Color.Red, fontSize = 12.sp)
                }
            }
        }

        OutlinedButton(onClick = {
            keyBoardControlle?.hide()
            if(viewModel.validar()){
                viewModel.saveDivisor()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
            Text(text = "Guardar")
        }
        Spacer(modifier = Modifier.width(12.dp))
        consultaDivisor(divisor = divisores, viewModel)
    }
}



@Composable
fun consultaDivisor(divisor: List<Divisor> ,viewModel: DivisorViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Text(text = "Historial De Resultados", style = MaterialTheme.typography.titleMedium)
            Icon(imageVector = Icons.Filled.Info, contentDescription = "Info icon")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(divisor) { divisor ->
                ItemConsulta(divisor)
            }
        }
    }
}

@Composable
fun ItemConsulta(divisor: Divisor, viewModel: DivisorViewModel = hiltViewModel()) {
    Divider(Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            //Nombre
            Text(text = "${divisor.nombre}")
            //Dividendo y Divisor
            Row {
                Text(text = "Dividendo: ${divisor.dividendo}")
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Divisor: ${divisor.divisor}")
            }
            // Cociente y Residuo
            Row {
                Text(text = "Cociente: ${divisor.cociente}")
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Residuo: ${divisor.residuo}")
            }
        }
        //Delete
        Column(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text(
                text = "Delete",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Button(
                onClick = {
                    viewModel.deleteDivisor(divisor)
                }
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}