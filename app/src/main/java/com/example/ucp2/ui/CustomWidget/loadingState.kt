package com.example.ucp2.ui.CustomWidget

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun loadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = "Loading...",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}