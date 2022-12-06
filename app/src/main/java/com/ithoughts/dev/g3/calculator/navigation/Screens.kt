package com.ithoughts.dev.g3.calculator.navigation

sealed class Screens(val route: String) {
    object CalculatorScreen: Screens("standard_screen")
    object ScientificScreen: Screens("scientific_screen")
    object ProgrammerCal: Screens("programmer_screen")

    object ConverterScreen: Screens("converters_screen")
}