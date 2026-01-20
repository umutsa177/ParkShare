package com.umutsayar.parkshare.presentation.add_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.add_listing.BluePrimary
import com.umutsayar.parkshare.presentation.add_listing.TextGray

@Composable
fun AddDetailsScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Detaylar ve Konum", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Text("İlan Başlığı", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Örn: Şehir Merkezi Kapalı Otopark") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("Açıklama", fontWeight = FontWeight.Medium)
            Text("Maks. 500 karakter", fontSize = 12.sp, color = TextGray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Park yeri özellikleri, güvenlik durumu vb. detayları ekleyin.") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = BluePrimary)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Adres", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))

        // Harita Görseli (Placeholder)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFCBE6CB)), // Harita yeşili
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Icon(Icons.Default.Map, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Haritada Seç")
            }
        }
        Text("Konum doğruluğu için haritadan işaretleyiniz.", fontSize = 12.sp, color = TextGray, modifier = Modifier.padding(top = 8.dp))
    }
}