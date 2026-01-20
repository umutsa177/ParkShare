package com.umutsayar.parkshare.presentation.add_listing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.add_listing.components.AddDetailsScreen
import com.umutsayar.parkshare.presentation.add_listing.components.AddPhotosScreen
import com.umutsayar.parkshare.presentation.add_listing.components.AddPricingAndFeatureScreen
import com.umutsayar.parkshare.presentation.add_listing.components.BottomButtonBar

// Tema Renkleri
val BluePrimary = Color(0xFF2563EB)
val LightBlueBg = Color(0xFFEBF2FF)
val TextGray = Color(0xFF6B7280)
val BorderGray = Color(0xFFE5E7EB)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListingScreen() {
    var currentStep by remember { mutableIntStateOf(1) }
    val totalSteps = 3

    // İlerleme çubuğu değeri
    val progress = currentStep.toFloat() / totalSteps.toFloat()

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = when (currentStep) {
                                1 -> "İlan Ekle"
                                2 -> "Yeni İlan Oluştur"
                                else -> "Fiyatlandırma ve Özellikler"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { if (currentStep > 1) currentStep-- }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                        }
                    },
                    actions = {
                        if (currentStep == 2) {
                            TextButton(onClick = { /* İptal */ }) {
                                Text("İptal", color = TextGray)
                            }
                        }
                    }
                )
                // Progress Bar ve Adım Metni
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Adım $currentStep/$totalSteps",
                            color = BluePrimary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        if (currentStep == 2) Text("Detaylar & Konum", fontSize = 12.sp, color = TextGray)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = BluePrimary,
                        trackColor = LightBlueBg,
                    )
                }
            }
        },
        bottomBar = {
            BottomButtonBar(
                currentStep = currentStep,
                totalSteps = totalSteps,
                onNext = { if (currentStep < totalSteps) currentStep++ },
                onBack = { if (currentStep > 1) currentStep-- },
                onFinish = { /* Kaydet ve Yayınla işlemi */ }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (currentStep) {
                1 -> AddPhotosScreen()
                2 -> AddDetailsScreen()
                3 -> AddPricingAndFeatureScreen()
            }
        }
    }
}