package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ConversionScreen(
    category: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Coming soon", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge)
    }
}