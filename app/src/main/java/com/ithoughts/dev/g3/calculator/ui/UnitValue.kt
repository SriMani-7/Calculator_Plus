@file:OptIn(ExperimentalMaterial3Api::class)

package com.ithoughts.dev.g3.calculator.ui

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ithoughts.dev.g3.calculator.logic.ConversionUnit
import com.ithoughts.dev.g3.calculator.viewmodels.UnitState

@Composable
fun UnitValue(
    unitState: UnitState,
    options: List<ConversionUnit>,
    onFocus: () -> Unit,
    onSelected: (ConversionUnit) -> Unit
) {
    var menuOpened by remember { mutableStateOf(false) }

    TextButton(onClick = { menuOpened = true }) {
        Text(text = unitState.unit.unitName, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = "Menu",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        DropdownMenu(expanded = menuOpened, onDismissRequest = { menuOpened = false } ) {
            options.forEach {
                DropdownMenuItem(
                    contentPadding = PaddingValues(start = 20.dp, end = 30.dp),
                    text = { Text(it.unitName) }, onClick = {
                        onSelected(it)
                        menuOpened = false
                    }
                )
            }
        }
    }
    OutlinedTextField(
        unitState.value,
        onValueChange = {},
        readOnly = true,
        textStyle = if (unitState.value.length < 20) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
        placeholder = { Text(text = "0", style = MaterialTheme.typography.titleMedium) },
        interactionSource = remember { MutableInteractionSource() }.also {
            LaunchedEffect(it) {
                it.interactions.collect { interaction ->
                    if (interaction is FocusInteraction.Focus) onFocus()
                }
            }
        }
    )
}