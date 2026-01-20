package com.umutsayar.parkshare.presentation.bookings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umutsayar.parkshare.presentation.bookings.model.BookingStatus

@Composable
fun StatusChip(status: BookingStatus) {
    val text: String
    val backgroundColor: Color

    when (status) {
        BookingStatus.APPROVED -> {
            text = "Onaylandı"
            backgroundColor = Color(0xFFDFF5E1)
        }
        BookingStatus.PENDING -> {
            text = "Bekliyor"
            backgroundColor = Color(0xFFFFF3CD)
        }
        BookingStatus.COMPLETED -> {
            text = "Tamamlandı"
            backgroundColor = Color(0xFFE0E0E0)
        }
    }


    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}
