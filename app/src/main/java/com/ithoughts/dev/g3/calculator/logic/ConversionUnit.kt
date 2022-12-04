package com.ithoughts.dev.g3.calculator.logic

data class ConversionUnit(
    val category: Category,
    val unitShort: String?,
    val unitName: String,
    var factor: Double,
)