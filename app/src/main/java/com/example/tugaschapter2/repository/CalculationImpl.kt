package com.example.tugaschapter2.repository

import kotlin.math.ceil

class CalculationImpl : Calculation {
    override fun calculateTip(
        costOfService: Double,
        tipPercentage: Double,
        roundUp: Boolean
    ): Double {
        return calculate(costOfService, tipPercentage, roundUp)
    }

    private fun calculate(costOfService: Double, tipPercentage: Double, roundUp: Boolean): Double {
        var tipAmount = costOfService * tipPercentage
        if (roundUp) {
            tipAmount = ceil(tipAmount)
        }
        return tipAmount
    }
}