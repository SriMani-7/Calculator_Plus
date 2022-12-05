package com.ithoughts.dev.g3.calculator.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithoughts.dev.g3.calculator.logic.ConversionFactors
import com.ithoughts.dev.g3.calculator.logic.ConversionFactors.allUnits
import com.ithoughts.dev.g3.calculator.logic.ConversionUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConverterViewModel(application: Application) : AndroidViewModel(application) {
    private val _optionsState = MutableStateFlow(emptyList<ConversionUnit>())
    val optionsState = _optionsState.asStateFlow()
    private val _first = MutableStateFlow<UnitState?>(null)
    val first = _first.asStateFlow()
    private val _second = MutableStateFlow<UnitState?>(null)
    val second = _second.asStateFlow()
    private var withFirst = true
    private var pointer = false
    private var input = ""

    suspend fun updateCategory(category: String) {
        withContext(Dispatchers.IO) {
            _optionsState.update {
                allUnits().filter { it.category.name == category }
            }
            _first.update { UnitState("", _optionsState.value[0]) }
            _second.update { UnitState("", _optionsState.value[1]) }
        }
    }

    fun updateOption(first: Boolean, conversionUnit: ConversionUnit) {
        if (first) _first.update { it?.copy(unit = conversionUnit) }
        else _second.update { it?.copy(unit = conversionUnit) }
        _first.value?.let { convert() }
    }

    private fun updateValue(first: Boolean, value: String) {
        if (first) _first.update { it?.copy(value = value) }
        else _second.update { it?.copy(value = value) }
    }

    fun updateInput(key: String) {
        if (pointer) {
            input = ""
            pointer = false
        }
        input = when (key) {
            "CE" -> ""
            "back" -> if (input.isNotEmpty()) input.dropLast(1) else ""
            "." -> if (input.isEmpty()) "0." else if (input.contains(".")) input else input + key
            else -> input + key
        }
        convert()
    }

    fun changeFrom(first: Boolean) {
        withFirst = first
        pointer = true
    }

    private fun convert() {
        val value = input
        updateValue(withFirst, value)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (value.isNotBlank())
                    _first.value?.let { f ->
                        _second.value?.let { s ->
                            val result = ConversionFactors.convert(
                                value = value.toDouble(),
                                from = if (withFirst) f.unit else s.unit,
                                to = if (withFirst) s.unit else f.unit
                            )
                            updateValue(!withFirst, "$result")
                        }
                    } else updateValue(!withFirst, "")
            }
        }
    }
}

data class UnitState(
    val value: String,
    val unit: ConversionUnit
)