package com.ithoughts.dev.g3.calculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ithoughts.dev.g3.calculator.R


@Composable
fun ConverterNumberPad(onKeyEvent: (String) -> Unit) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(12.dp, 16.dp),
            userScrollEnabled = false
        ) {
            ("7 8 9 back 4 5 6 CE 1 2 3 .").split(" ").forEach { key ->
                item {
                    CVNumberKey(key, onClick = onKeyEvent)
                }
            }
            item(span = { GridItemSpan(3) }) {
                CVNumberKey("0", onClick = onKeyEvent)
            }
        }
    }
}

@Composable
fun CVNumberKey(
    key: String,
    enabled: Boolean = true,
    onClick: (String) -> Unit
) {
    TextButton(
        enabled = enabled,
        onClick = { onClick(key) },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        when (key) {
            "back" -> Icon(
                ImageVector.vectorResource(R.drawable.ic_round_backspace_24),
                "back",
                tint = MaterialTheme.colorScheme.secondary
            )
            else -> Text(key, fontFamily = SansSerif, fontSize = 20.sp)
        }
    }
}