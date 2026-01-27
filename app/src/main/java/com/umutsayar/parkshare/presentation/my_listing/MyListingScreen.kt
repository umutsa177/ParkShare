package com.umutsayar.parkshare.presentation.my_listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.umutsayar.parkshare.domain.model.ListingStatus
import com.umutsayar.parkshare.domain.model.MyListing
import com.umutsayar.parkshare.presentation.my_listing.components.EarningsCard
import com.umutsayar.parkshare.presentation.my_listing.components.EmptyListingsState
import com.umutsayar.parkshare.presentation.my_listing.components.ListingGridCard
import com.umutsayar.parkshare.presentation.my_listing.components.ListingListCard

// --- RENKLER ---
val BluePrimary = Color(0xFF2563EB)
val TextDark = Color(0xFF1F2937)
val TextGray = Color(0xFF6B7280)
val BgLight = Color(0xFFF9FAFB)
val GreenActive = Color(0xFF4CAF50)
val StarYellow = Color(0xFFFBC02D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListingsScreen(
    onNavigateToAddListing: () -> Unit = {}
) {
    // Başlangıç verisi (Boş durumu test etmek için listeyi boşaltabilirsiniz)
    val listings = remember {
        mutableStateListOf(
            MyListing("1", "Kapalı Garaj", "Caferağa Mah. Moda Cd.", "₺25", 4.5, 8, "https://via.placeholder.com/150", ListingStatus.ACTIVE, 124, 8),
            MyListing("2", "Açık Otopark", "Sinanpaşa Mah. Beşiktaş", "₺30", 4.8, 12, "https://via.placeholder.com/150", ListingStatus.PASSIVE, 45, 2),
            MyListing("3", "Özel Garaj - Şişli", "Meşrutiyet Mah.", "₺45", 0.0, 0, "https://via.placeholder.com/150", ListingStatus.ACTIVE, 89, 3),
            MyListing(
                "4",
                "Yeni İlan",
                "Taslak Aşamasında",
                "--",
                0.0,
                0,
                "",
                ListingStatus.DRAFT,
                0,
                0
            )
        )
    }

    var isGridView by remember { mutableStateOf(true) } // Grid/List Toggle State

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (listings.isEmpty()) "İlanlarım" else "Panelim", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = BgLight),
                actions = {
                    // Kullanıcı ikonu (Resim 1'deki gibi)
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profil", tint = TextGray)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddListing,
                containerColor = BluePrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp) // Yuvarlak kare form
            ) {
                Icon(Icons.Default.Add, contentDescription = "İlan Ekle", modifier = Modifier.size(32.dp))
            }
        },
        containerColor = BgLight
    ) { paddingValues ->

        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {

            if (listings.isEmpty()) {
                EmptyListingsState(onNavigateToAddListing)
            } else {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    EarningsCard()

                    Spacer(modifier = Modifier.height(24.dp))

                    // Başlık ve Toggle Butonları
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "İlanlarım (${listings.size})",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        // Grid / List Değiştirici
                        Row(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(4.dp)
                        ) {
                            IconToggleButton(
                                checked = isGridView,
                                onCheckedChange = { isGridView = true },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    Icons.Default.GridView,
                                    contentDescription = "Grid",
                                    tint = if (isGridView) BluePrimary else TextGray
                                )
                            }
                            IconToggleButton(
                                checked = !isGridView,
                                onCheckedChange = { isGridView = false },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ViewList,
                                    contentDescription = "List",
                                    tint = if (!isGridView) BluePrimary else TextGray
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Liste İçeriği (Grid veya List)
                    if (isGridView) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = 80.dp) // FAB için boşluk
                        ) {
                            items(listings) { listing ->
                                ListingGridCard(listing)
                            }
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = 80.dp)
                        ) {
                            items(listings) { listing ->
                                ListingListCard(listing)
                            }
                        }
                    }
                }
            }
        }
    }
}



