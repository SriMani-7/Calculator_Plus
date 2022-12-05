package com.ithoughts.dev.g3.calculator.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SNumberPad(onKeyPress: (String) -> Unit) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp),
            userScrollEnabled = false
        ) {
            "C / back % 7 8 9 * 4 5 6 - 1 2 3 + +/- 0 .".split(" ").forEach { key ->
                item {
                    CVNumberKey(key, onKeyPress)
                }
            }
            item {
                Button(
                    onClick = { onKeyPress("=") },
                    contentPadding = PaddingValues(vertical = 18.dp),
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("=", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                }
            }
        }
    }
}