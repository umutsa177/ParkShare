package com.umutsayar.parkshare.presentation.my_listing.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.umutsayar.parkshare.domain.model.ListingStatus
import com.umutsayar.parkshare.domain.model.MyListing
import com.umutsayar.parkshare.presentation.main.ContainerBadge
import com.umutsayar.parkshare.presentation.main.StatItem
import com.umutsayar.parkshare.presentation.my_listing.BluePrimary
import com.umutsayar.parkshare.presentation.my_listing.GreenActive
import com.umutsayar.parkshare.presentation.my_listing.TextGray

@Composable
fun ListingGridCard(listing: MyListing) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // Görsel ve Durum Rozeti
            Box(modifier = Modifier.height(120.dp).fillMaxWidth()) {
                if (listing.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = listing.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(Modifier.fillMaxSize().background(Color(0xFFEEEEEE)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.PhotoCamera, contentDescription = null, tint = Color.Gray)
                    }
                }

                // Status Badge (Aktif/Pasif/Taslak)
                Box(modifier = Modifier.padding(8.dp)) {
                    val (bgColor, txtColor, text) = when(listing.status) {
                        ListingStatus.ACTIVE -> Triple(Color(0xFFE8F5E9), GreenActive, "Aktif")
                        ListingStatus.PASSIVE -> Triple(Color(0xFFEEEEEE), TextGray, "Pasif")
                        ListingStatus.DRAFT -> Triple(Color(0xFFE3F2FD), BluePrimary, "Taslak")
                    }
                    ContainerBadge(color = bgColor) {
                        Text(text, color = txtColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // İçerik
            Column(modifier = Modifier.padding(12.dp)) {
                // Başlık ve Menü
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(listing.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, modifier = Modifier.weight(1f))
                    Icon(Icons.Default.MoreVert, contentDescription = null, tint = TextGray, modifier = Modifier.size(16.dp))
                }

                Text("${listing.price}/saat", color = BluePrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(12.dp))

                // İstatistikler (Göz ve Takvim)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatItem(Icons.Outlined.Visibility, "${listing.viewCount}")
                    StatItem(Icons.Outlined.CalendarToday, "${listing.reservationCount} rez.")
                }
            }
        }
    }
}