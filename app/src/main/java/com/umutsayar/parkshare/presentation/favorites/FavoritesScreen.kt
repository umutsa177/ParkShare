package com.umutsayar.parkshare.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umutsayar.parkshare.domain.model.FavoritePark
import com.umutsayar.parkshare.presentation.favorites.components.*

val BluePrimary = Color(0xFF2563EB)
val RedDelete = Color(0xFFEF4444)
val TextDark = Color(0xFF1F2937)
val TextGray = Color(0xFF6B7280)
val BackgroundLight = Color(0xFFF9FAFB)
val StarYellow = Color(0xFFF59E0B)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onNavigateBack: () -> Unit = {},
    onExploreClicked: () -> Unit = {}
) {

    // Başlangıçta dolu bir liste simüle ediyoruz
    val initialList = remember {
        mutableStateListOf(
            FavoritePark(
                "1",
                "Şişli, İstanbul",
                "₺25/saat",
                4.5,
                "2.3 km",
                "https://via.placeholder.com/300"
            ),
            FavoritePark("2", "Beşiktaş, İstanbul", "₺30/saat", 4.8, "1.1 km", "https://via.placeholder.com/300"),
            FavoritePark("3", "Kadıköy, İstanbul", "₺20/saat", 4.2, "4.5 km", "https://via.placeholder.com/300"),
            FavoritePark("4", "Ataşehir, İstanbul", "₺18/saat", 4.6, "3.8 km", "https://via.placeholder.com/300"),
            FavoritePark("5", "Üsküdar, İstanbul", "₺22/saat", 4.9, "0.8 km", "https://via.placeholder.com/300"),
            FavoritePark("6", "Sarıyer, İstanbul", "₺40/saat", 4.7, "5.2 km", "https://via.placeholder.com/300")
        )
    }

    // Silme Dialogu kontrolü
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<FavoritePark?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Favorilerim", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Geri")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Arama */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Ara")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundLight
                )
            )
        },
        containerColor = BackgroundLight
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

            if (initialList.isEmpty()) {
                EmptyFavoritesState(onExploreClicked)
            } else {
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(items = initialList, key = { it.id }) { park ->

                            val dismissState = rememberSwipeToDismissBoxState(
                                confirmValueChange = {
                                    if (it == SwipeToDismissBoxValue.EndToStart) {
                                        itemToDelete = park
                                        showDeleteDialog = true
                                        return@rememberSwipeToDismissBoxState false
                                    }
                                    false
                                }
                            )

                            SwipeToDismissBox(
                                state = dismissState,
                                backgroundContent = {
                                    val color = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) RedDelete else Color.Transparent
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(color)
                                            .padding(end = 16.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            Icons.Outlined.Delete,
                                            contentDescription = "Sil",
                                            tint = Color.White
                                        )
                                    }
                                },
                                enableDismissFromStartToEnd = false,
                                content = {
                                    FavoriteItemCard(
                                        park = park,
                                        onHeartClick = {
                                            itemToDelete = park
                                            showDeleteDialog = true
                                        }
                                    )
                                }
                            )
                        }
                    }

                    // Alt Bilgi
                    Text(
                        text = "${initialList.size} Favori Park Yeri",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = TextGray
                    )
                }
            }
        }
    }

    // --- SİLME DIALOGU  ---
    if (showDeleteDialog && itemToDelete != null) {
        DeleteConfirmationDialog(
            onDismiss = {
                showDeleteDialog = false
                itemToDelete = null
            },
            onConfirm = {
                itemToDelete?.let {
                    initialList.remove(it)
                }
                showDeleteDialog = false
                itemToDelete = null
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}