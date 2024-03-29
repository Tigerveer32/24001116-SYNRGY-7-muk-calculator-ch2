package com.example.tugaschapter2.common

import com.example.tugaschapter2.repository.Calculation


fun Double.calculate(calculation: Calculation, tipPercentage: Double, roundUp: Boolean): Double {
    return calculation.calculateTip(this, tipPercentage, roundUp)
}