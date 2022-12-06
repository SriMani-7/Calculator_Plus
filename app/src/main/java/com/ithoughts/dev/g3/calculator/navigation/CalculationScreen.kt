package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ithoughts.dev.g3.calculator.ui.SNumberPad
import com.ithoughts.dev.g3.calculator.viewmodels.CalculatorViewModel

@Composable
fun CalculationScreen() {
    val viewModel: CalculatorViewModel = viewModel()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp)
        ) {
            Text(
                textAlign = TextAlign.End,
                text = viewModel.result.ifBlank { "0" },
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraLight
            )
            BasicTextField(
                value = viewModel.input.ifBlank { "0" },
                onValueChange = {},
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                maxLines = 3
            )
        }
        SNumberPad(viewModel::updateInput)
    }
}