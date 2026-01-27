package com.umutsayar.parkshare.presentation.my_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.umutsayar.parkshare.domain.model.ListingStatus
import com.umutsayar.parkshare.domain.model.MyListing
import com.umutsayar.parkshare.presentation.my_listing.StarYellow
import com.umutsayar.parkshare.presentation.my_listing.TextGray

@Composable
fun ListingListCard(listing: MyListing) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Görsel
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(80.dp)
            ) {
                if(listing.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = listing.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(Modifier.fillMaxSize().background(Color.LightGray))
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Bilgiler
            Column(modifier = Modifier.weight(1f)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(listing.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Icon(Icons.Default.Edit, contentDescription = "Düzenle", tint = TextGray, modifier = Modifier.size(18.dp))
                }

                Text(listing.location, fontSize = 12.sp, color = TextGray, maxLines = 1)
                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${listing.price}/saat", color = Color(0xFF6200EE), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(12.dp))

                    if (listing.rating > 0) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = StarYellow, modifier = Modifier.size(14.dp))
                        Text(" ${listing.rating}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Alt Bar (Switch ve Detay)
        Divider(color = Color(0xFFF0F0F0))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Switch (Aktif/Pasif)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = listing.status == ListingStatus.ACTIVE,
                    onCheckedChange = {},
                    modifier = Modifier.scale(0.8f),
                    colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Color(0xFF6200EE))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(if(listing.status == ListingStatus.ACTIVE) "Aktif" else "Pasif", fontSize = 12.sp, color = if(listing.status == ListingStatus.ACTIVE) Color(0xFF6200EE) else TextGray)
            }

            // Detay Linki
            Row(modifier = Modifier.clickable { }, verticalAlignment = Alignment.CenterVertically) {
                Text("Detaylar", color = Color(0xFF6200EE), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF6200EE), modifier = Modifier.size(16.dp))
            }
        }
    }
}