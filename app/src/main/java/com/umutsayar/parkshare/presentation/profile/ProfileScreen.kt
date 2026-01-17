package com.umutsayar.parkshare.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToEditProfile: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToPaymentMethods: () -> Unit = {},
    onNavigateToLanguage: () -> Unit = {},
    onNavigateToHelp: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profilim",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Image with Verified Badge
            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = "https://via.placeholder.com/120",
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Crop
                )

                // Verified Badge
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2196F3)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Verified",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = "Ahmet Yılmaz",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "4.9",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFF1A1A1A)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Member Since
            Text(
                text = "Haziran 2023'den beri üye",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF757575)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Statistics Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    icon = Icons.Default.CalendarToday,
                    title = "Toplam Rezervasyon",
                    value = "24",
                    iconColor = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    icon = Icons.Default.AccountBalanceWallet,
                    title = "Toplam Kazanç",
                    value = "₺3,450",
                    iconColor = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Settings List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                SettingsItem(
                    icon = Icons.Default.Edit,
                    title = "Profil Düzenle",
                    iconColor = Color(0xFF2196F3),
                    onClick = onNavigateToEditProfile
                )

                Spacer(modifier = Modifier.height(8.dp))

                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Bildirimler",
                    iconColor = Color(0xFF9C27B0),
                    onClick = onNavigateToNotifications
                )

                Spacer(modifier = Modifier.height(8.dp))

                SettingsItem(
                    icon = Icons.Default.CreditCard,
                    title = "Ödeme Yöntemleri",
                    iconColor = Color(0xFF4CAF50),
                    onClick = onNavigateToPaymentMethods
                )

                Spacer(modifier = Modifier.height(8.dp))

                SettingsItem(
                    icon = Icons.Default.Language,
                    title = "Dil Seçimi",
                    iconColor = Color(0xFFFF9800),
                    trailing = {
                        Text(
                            text = "Türkçe",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF757575)
                        )
                    },
                    onClick = onNavigateToLanguage
                )

                Spacer(modifier = Modifier.height(8.dp))

                SettingsItem(
                    icon = Icons.Default.Help,
                    title = "Yardım & Destek",
                    iconColor = Color(0xFF00BCD4),
                    onClick = onNavigateToHelp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout",
                    tint = Color(0xFFF44336),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Çıkış Yap",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFFF44336)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // App Version
            Text(
                text = "ParkShare v2.4.0",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFBDBDBD),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StatCard(
    icon: ImageVector,
    title: String,
    value: String,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF757575),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF1A1A1A)
            )
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    iconColor: Color,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF1A1A1A),
                modifier = Modifier.weight(1f)
            )

            if (trailing != null) {
                trailing()
                Spacer(modifier = Modifier.width(8.dp))
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate",
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


