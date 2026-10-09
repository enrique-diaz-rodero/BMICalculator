package com.enriquediaz.bmicalculatorjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.enriquediaz.bmicalculatorjetpackcompose.ui.theme.BMICalculatorJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BMIScreen(){

    // Variables
    var nombre: String by remember { mutableStateOf("") }
    var peso: Int by remember { mutableStateOf(80) }
    var altura: Int by remember { mutableStateOf(180) }
    var bmi: Float  by remember { mutableStateOf(0f) }

    Column(){

        // Titulo
    Text(text = "Calculadora IMC")
    // Introducir nombre
    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = { Text(text = "Nombre") }
    )
        // Row de El peso y el boton para aumentar o disminuir el peso
        Row(){
            Text(text = "Peso: $peso Kg")
            Button(
                onClick = {peso--}
            ) {
                Text(text = "-")
            }
            Button(
                onClick = {peso++}
            ) {
                Text(text = "+")
            }

        }

        // Cajita de alura + slider para aumentar y disminuir
        Text(text = "Altura: $altura cm")
        Slider(
            value = altura.toFloat(),
            onValueChange = {altura = it.toInt()},
            valueRange = 100f..250f
        )

        // Boton para calcular el BMI
        Button(onClick = {
            val alturaMetros = altura / 100f
            bmi = peso / (alturaMetros * alturaMetros)
        }) {
            Text(text = "Calcular IMC")
        }

        // Condicional para que no aparezca EL CALCULO DEL BMI
        if(bmi != 0f){
            Text(text = "Tu IMC es: $bmi")
        }
}
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculatorJetpackComposeTheme {
        BMIScreen()

    }
}