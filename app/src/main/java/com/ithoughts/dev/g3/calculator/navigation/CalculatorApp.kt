@file:OptIn(ExperimentalMaterial3Api::class)

package com.ithoughts.dev.g3.calculator.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.ithoughts.dev.g3.calculator.logic.Category
import com.ithoughts.dev.g3.calculator.ui.CalculatorAppbar
import kotlinx.coroutines.launch

@Composable
fun CalculatorApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedCategory by remember { mutableStateOf<Category>(Category.StandardCALCULATOR) }
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
                    navController.navigate(destination, navOptions = navOptions {
                        popUpTo(0)
                    })
                }
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
                    selectedCategory = Category.StandardCALCULATOR
                    CalculationScreen()
                }
                composable(Screens.ScientificScreen.route) {
                    selectedCategory = Category.ScientificCALCULATOR
                    ComingSoon()
                }
                composable(Screens.ProgrammerCal.route) {
                    selectedCategory = Category.ProgrammerCALCULATOR
                    ComingSoon()
                }
                composable("${Screens.ConverterScreen.route}/{category}", arguments = listOf(
                    navArgument("category") { type = NavType.StringType }
                )) { stackEntry ->
                    stackEntry.arguments?.getString("category")?.let { s ->
                        selectedCategory = Category.values().first { it.name == s }
                        ConversionScreen(s)
                    }
                }
            }
        }
    }
}

@Composable
fun ComingSoon() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Coming soon...",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.displayMedium
        )
    }
}