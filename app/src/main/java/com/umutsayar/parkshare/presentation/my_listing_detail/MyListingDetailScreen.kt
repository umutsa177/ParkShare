package com.umutsayar.parkshare.presentation.my_listing_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.my_listing.BgLight
import com.umutsayar.parkshare.presentation.my_listing.BluePrimary
import com.umutsayar.parkshare.presentation.my_listing_detail.componets.ApprovedReservationItem
import com.umutsayar.parkshare.presentation.my_listing_detail.componets.HeaderImageSection
import com.umutsayar.parkshare.presentation.my_listing_detail.componets.PendingReservationCard
import com.umutsayar.parkshare.presentation.my_listing_detail.componets.StatCardItem
import com.umutsayar.parkshare.presentation.my_listing_detail.componets.StatusCard

// --- RENKLER ---
val RedDanger = Color(0xFFEF4444)
val GreenSuccess = Color(0xFF22C55E)
val OrangeWarning = Color(0xFFF59E0B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingDetailScreen(
    onNavigateBack: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    // Scroll state
    val scrollState = rememberScrollState()

    // Switch state
    var isActive by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("İlan Detayı", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Menü */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Daha Fazla")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = BgLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 24.dp) // Bottom padding
        ) {
            // Üst Görsel Alanı
            HeaderImageSection()

            Column(modifier = Modifier.padding(16.dp)) {

                // İlan Durumu Kartı
                StatusCard(isActive = isActive, onStatusChange = { isActive = it })

                Spacer(modifier = Modifier.height(16.dp))

                // İstatistikler
                StatisticsSection()

                Spacer(modifier = Modifier.height(24.dp))

                // Rezervasyonlar Başlığı
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Gelen Rezervasyonlar", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    TextButton(onClick = { /* Tümünü gör */ }) {
                        Text("Tümünü Gör", color = BluePrimary)
                    }
                }

                PendingReservationCard()

                Spacer(modifier = Modifier.height(12.dp))

                ApprovedReservationItem(name = "Ayşe Y.", date = "15 Eki, 09:00 - 18:00")
                Spacer(modifier = Modifier.height(8.dp))
                ApprovedReservationItem(name = "Mehmet K.", date = "18 Eki, 08:30 - 10:30")

                Spacer(modifier = Modifier.height(32.dp))

                // Alt Aksiyon Butonları
                Button(
                    onClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("İlanı Düzenle", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { /* Silme Dialogu */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, RedDanger),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = RedDanger)
                ) {
                    Icon(Icons.Outlined.Delete, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("İlanı Sil", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun StatisticsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCardItem(
            icon = Icons.Default.Visibility,
            value = "245",
            label = "Toplam\nGörüntülenme",
            color = BluePrimary,
            modifier = Modifier.weight(1f)
        )
        StatCardItem(
            icon = Icons.Default.DateRange,
            value = "18",
            label = "Toplam\nRezervasyon",
            color = OrangeWarning,
            modifier = Modifier.weight(1f)
        )
        StatCardItem(
            icon = Icons.Default.AccountBalanceWallet, // Wallet ikonu yoksa Money
            value = "₺1,200",
            label = "Aylık\nKazanç",
            color = GreenSuccess,
            modifier = Modifier.weight(1f)
        )
    }
}




