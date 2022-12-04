@file:OptIn(ExperimentalMaterial3Api::class)

package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ithoughts.dev.g3.calculator.logic.Category
import com.ithoughts.dev.g3.calculator.ui.CalculatorAppbar
import kotlinx.coroutines.launch

@Composable
fun CalculatorApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerContent = {
        CalculatorDrawer(
            selected = Category.StandardCALCULATOR,
            onCategoryClick = { scope.launch { drawerState.close() } }
        )
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            CalculatorAppbar(title = "") {
                scope.launch { drawerState.open() }
            }
        }) { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
            ) {
                Text(
                    "Updated soon",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}