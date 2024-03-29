package com.example.tugaschapter2.ui

import androidx.lifecycle.ViewModel
import com.example.tugaschapter2.repository.CalculationImpl
import com.example.tugaschapter2.common.calculate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _tipAmount = MutableStateFlow(0.0)
    val tipAmount: StateFlow<Double> = _tipAmount

    fun calculateTip(costOfService: Double, tipPercentage: Double, roundUp: Boolean) {
        val tipAmount = costOfService.calculate(CalculationImpl(), tipPercentage, roundUp)
        _tipAmount.value = tipAmount
    }
}