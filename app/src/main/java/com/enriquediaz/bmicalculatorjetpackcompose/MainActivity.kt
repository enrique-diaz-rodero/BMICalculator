package com.enriquediaz.bmicalculatorjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enriquediaz.bmicalculatorjetpackcompose.ui.theme.BMICalculatorJetpackComposeTheme
import com.enriquediaz.bmicalculatorjetpackcompose.ui.theme.FitnessPrimary
import com.enriquediaz.bmicalculatorjetpackcompose.ui.theme.FitnessSecondary
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMIScreen()
                }
            }
        }
    }
}

@Composable
fun BMIScreen() {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf(70) }
    var altura by remember { mutableStateOf(170) }
    var bmi by remember { mutableStateOf(0f) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // App Header / Hero Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                FitnessPrimary,
                                FitnessSecondary
                            )
                        )
                    )
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🏋️‍♂️", fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "PulseFit IMC",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡Construye tu mejor versión! Mide tu índice y regula tu salud cada día.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Name Input Card
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "¿Cómo te llamas, atleta?") },
            leadingIcon = {
                Text(text = "👤", fontSize = 20.sp)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Weight Selector Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⚖️ PESO ACTUAL",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$peso kg",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { if (peso > 30) peso -= 5 },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Text("-5 kg", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Button(
                        onClick = { if (peso > 10) peso-- },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("-1 kg")
                    }
                    Button(
                        onClick = { peso++ },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("+1 kg")
                    }
                    Button(
                        onClick = { if (peso < 300) peso += 5 },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Text("+5 kg", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Height Selector Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "📏 ALTURA",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$altura cm",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(12.dp))
                Slider(
                    value = altura.toFloat(),
                    onValueChange = { altura = it.toInt() },
                    valueRange = 120f..220f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Calculate Button
        Button(
            onClick = {
                val alturaMetros = altura / 100f
                if (alturaMetros > 0f) {
                    bmi = peso / (alturaMetros * alturaMetros)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = if (nombre.isBlank()) "CALCULAR MI IMC" else "¡CALCULAR IMC DE ${nombre.uppercase()}!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Result & Motivation Card
        AnimatedVisibility(
            visible = bmi > 0f,
            enter = fadeIn() + slideInVertically()
        ) {
            val (category, description, colorBadge) = getBmiFeedback(bmi)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (nombre.isNotBlank()) "RESULTADO PARA $nombre" else "TU RESULTADO DE IMC",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = String.format(Locale.US, "%.1f", bmi),
                        fontSize = 54.sp,
                        fontWeight = FontWeight.Black,
                        color = colorBadge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = colorBadge.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = category,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                            color = colorBadge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = description,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "💡 Consejo Fitness: Mantén una hidratación constante (2L de agua al día), combina entrenamiento de fuerza y cardio, y prioriza el descanso para regular tu peso de forma saludable.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

data class BmiFeedback(val category: String, val description: String, val color: Color)

fun getBmiFeedback(bmi: Float): BmiFeedback {
    return when {
        bmi < 18.5f -> BmiFeedback(
            category = "⚖️ Peso Bajo",
            description = "Estás por debajo del peso recomendado. ¡Es momento de nutrirte con energía y sumar masa muscular con entrenamientos de fuerza! 💪",
            color = Color(0xFF0984E3)
        )
        bmi in 18.5f..24.9f -> BmiFeedback(
            category = "🌟 ¡Peso Saludable!",
            description = "¡Excelente trabajo! Te encuentras en tu rango ideal. Sigue entrenando y comiendo balanceado para mantener tu vitalidad. 🔥",
            color = Color(0xFF00B894)
        )
        bmi in 25.0f..29.9f -> BmiFeedback(
            category = "⚠️ Sobrepeso",
            description = "Pequeños cambios diarios en tu alimentación y una rutina constante de cardio te ayudarán a alcanzar tu peso ideal. ¡Tú puedes! 🏃‍♂️",
            color = Color(0xFFF39C12)
        )
        else -> BmiFeedback(
            category = "🔥 Obesidad (Atención Prioritaria)",
            description = "Tu salud es tu mayor riqueza. Da el primer paso hoy hacia un estilo de vida activo y consulta con un especialista para un plan personalizado. 🛡️",
            color = Color(0xFFE74C3C)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
fun BMIScreenPreview() {
    BMICalculatorJetpackComposeTheme(darkTheme = true) {
        BMIScreen()
    }
}
