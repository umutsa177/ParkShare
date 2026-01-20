package com.umutsayar.parkshare.presentation.add_listing.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.add_listing.BluePrimary
import com.umutsayar.parkshare.presentation.add_listing.LightBlueBg
import com.umutsayar.parkshare.presentation.add_listing.TextGray

@Composable
fun AddPhotosScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Park yerinizin fotoğraflarını yükleyin",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Kullanıcılar fotoğraflı ilanlara daha çok güvenir. En az 3 fotoğraf eklemenizi öneririz.",
            color = TextGray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Fotoğraf Yükleme Alanı (Dashed Border efekti için basit bir Box)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .border(
                    BorderStroke(1.dp, BluePrimary.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) // Not: Compose'da dashed border için özel draw modifier gerekir, basitlik için solid yapıldı.
                .background(BluePrimary.copy(alpha = 0.05f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.AddCircle, // Kamera ikonu yerine
                    contentDescription = "Yükle",
                    tint = BluePrimary,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Fotoğraf Yükle", color = BluePrimary, fontWeight = FontWeight.Bold)
                Text("Galeriden seç veya fotoğraf çek", color = TextGray, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // İpucu Kartı
        Card(
            colors = CardDefaults.cardColors(containerColor = LightBlueBg),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = null, tint = BluePrimary)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("İpucu", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(
                        "Geniş açılı ve aydınlık fotoğraflar park yerinizin daha hızlı kiralanmasını sağlar.",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                }
            }
        }
    }
}