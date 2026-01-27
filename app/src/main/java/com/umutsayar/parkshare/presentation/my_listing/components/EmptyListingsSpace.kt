package com.umutsayar.parkshare.presentation.my_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.my_listing.BluePrimary
import com.umutsayar.parkshare.presentation.my_listing.TextDark
import com.umutsayar.parkshare.presentation.my_listing.TextGray

@Composable
fun EmptyListingsState(onAddClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // İkon (Ev ve + Butonu)
        Box(contentAlignment = Alignment.BottomEnd) {
            Icon(
                Icons.Default.Garage, // Garage ikonu daha uygun
                contentDescription = null,
                tint = Color(0xFF7C4DFF).copy(alpha = 0.8f),
                modifier = Modifier.size(120.dp)
            )
            Icon(
                Icons.Default.AddCircle,
                contentDescription = null,
                tint = BluePrimary,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, androidx.compose.foundation.shape.CircleShape)
                    .padding(2.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Henüz İlan Eklemediniz",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "İlk park yeri ilanınızı oluşturarak kazanmaya başlayın",
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("İlk İlanımı Oluştur", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nasıl Çalışır?",
            color = BluePrimary,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { }
        )
    }
}