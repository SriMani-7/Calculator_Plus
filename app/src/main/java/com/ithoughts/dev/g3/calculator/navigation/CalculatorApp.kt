@file:OptIn(ExperimentalMaterial3Api::class)

package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ithoughts.dev.g3.calculator.logic.Category
import com.ithoughts.dev.g3.calculator.ui.CalculatorAppbar
import kotlinx.coroutines.launch

@Composable
fun CalculatorApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedCategory by remember { mutableStateOf<Category>(Category.LENGTH) }
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerContent = {
        CalculatorDrawer(
            selected = selectedCategory,
            onCategoryClick = {
                scope.launch {
                    drawerState.close()
                    val destination = when(it) {
                        is Category.StandardCALCULATOR -> Screens.CalculatorScreen.route
                        is Category.ScientificCALCULATOR -> Screens.ScientificScreen.route
                        is Category.ProgrammerCALCULATOR -> Screens.ProgrammerCal.route
                        else -> "${Screens.ConverterScreen.route}/${it.name}"
                    }
                    navController.navigate(destination)
                }
                selectedCategory = it
            }
        )
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            CalculatorAppbar(selectedCategory.name) {
                scope.launch { drawerState.open() }
            }
        }) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.CalculatorScreen.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screens.CalculatorScreen.route) {
                    CalculationScreen()
                }
                composable(Screens.ScientificScreen.route) {
                    CalculationScreen()
                }
                composable(Screens.ProgrammerCal.route) {
                    CalculationScreen()
                }
                composable("${Screens.ConverterScreen.route}/{category}", arguments = listOf(
                    navArgument("category") { type = NavType.StringType }
                )) { stackEntry ->
                    stackEntry.arguments?.getString("category")?.let {
                        ConversionScreen(it)
                    }
                }
            }
        }
    }
}