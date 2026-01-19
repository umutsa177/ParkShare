package com.umutsayar.parkshare.presentation.parking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ParkingMapScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        // MAP PLACEHOLDER
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F2F8))
        )

        SearchBar(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter)
        )

        MyLocationButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 140.dp)
        )

        ParkingPin(
            modifier = Modifier.align(Alignment.Center)
        )

        ParkingInfoCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}
