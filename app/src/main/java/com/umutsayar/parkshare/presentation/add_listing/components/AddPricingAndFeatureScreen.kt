package com.umutsayar.parkshare.presentation.add_listing.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.add_listing.BluePrimary
import com.umutsayar.parkshare.presentation.add_listing.BorderGray
import com.umutsayar.parkshare.presentation.add_listing.TextGray
import com.umutsayar.parkshare.presentation.add_listing.model.FeatureItem

@Composable
fun AddPricingAndFeatureScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Ücretlendirme", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        PricingInput("Saatlik Ücret")
        PricingInput("Günlük Ücret")
        PricingInput("Aylık Ücret")

        Spacer(modifier = Modifier.height(24.dp))
        Text("Özellikler", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        val features = listOf(
            FeatureItem("Kapalı Otopark", Icons.Default.Garage, true),
            FeatureItem("7/24 Güvenlik", Icons.Default.Security, false),
            FeatureItem("Kamera Sistemi", Icons.Default.Videocam, false),
            FeatureItem("Elektrikli Şarj", Icons.Default.ElectricCar, false)
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FeatureCard(features[0], Modifier.weight(1f))
            FeatureCard(features[1], Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FeatureCard(features[2], Modifier.weight(1f))
            FeatureCard(features[3], Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // İlanı Yayınla Switch
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, BorderGray),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("İlanı hemen yayınla", fontWeight = FontWeight.Bold)
                    Text("İlan onaylandıktan sonra görünür olur", fontSize = 12.sp, color = TextGray)
                }
                Switch(checked = true, onCheckedChange = {}, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = BluePrimary))
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun PricingInput(label: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(label, fontSize = 14.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = { Text("₺", color = TextGray) },
            placeholder = { Text("0.00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = BorderGray,
                focusedBorderColor = BluePrimary
            )
        )
    }
}


@Composable
fun FeatureCard(item: FeatureItem, modifier: Modifier = Modifier) {
    val borderColor = if (item.isSelected) BluePrimary else BorderGray
    val bgColor = if (item.isSelected) BluePrimary.copy(alpha = 0.1f) else Color.White

    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { /* Toggle Logic */ },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Box(modifier = Modifier.padding(12.dp).fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = if(item.isSelected) BluePrimary else Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.White, RoundedCornerShape(50))
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (item.isSelected) BluePrimary else Color.Black
                )
            }
            // Checkbox
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (item.isSelected) BluePrimary else Color.Transparent)
                    .border(1.dp, if(item.isSelected) BluePrimary else BorderGray, RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (item.isSelected) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

