package com.umutsayar.parkshare.presentation.favorites.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.domain.model.FavoritePark
import com.umutsayar.parkshare.presentation.favorites.BluePrimary
import com.umutsayar.parkshare.presentation.favorites.RedDelete
import com.umutsayar.parkshare.presentation.favorites.StarYellow
import com.umutsayar.parkshare.presentation.favorites.TextGray

@Composable
fun FavoriteItemCard(
    park: FavoritePark,
    onHeartClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Column {
            // Görsel ve Kalp Butonu
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                // Placeholder Resim (Gerçek projede AsyncImage kullanın)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)) {
                    // Coil AsyncImage buraya gelecek
                    // AsyncImage(model = park.imageUrl, ...)
                }

                // Kalp İkonu
                IconButton(
                    onClick = onHeartClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorilerden Çıkar",
                        tint = RedDelete,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Bilgiler
            Column(modifier = Modifier.padding(12.dp)) {
                // Konum
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = TextGray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = park.title,
                        fontSize = 12.sp,
                        color = TextGray,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Fiyat
                Text(
                    text = park.price,
                    color = BluePrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.weight(1f)) // Alta itmek için

                // Puan ve Mesafe
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = StarYellow, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${park.rating}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Text(text = park.distance, fontSize = 12.sp, color = TextGray)
                }
            }
        }
    }
}