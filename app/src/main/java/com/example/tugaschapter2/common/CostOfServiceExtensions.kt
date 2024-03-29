package com.example.tugaschapter2.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import com.example.tugaschapter2.databinding.ActivityMainBinding

fun EditText.costOfService(
    binding: ActivityMainBinding,
    listener: (costOfService: Double, tipPercentage: Double, roundUp: Boolean) -> Unit
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val costOfService = s.toString().toDoubleOrNull() ?: 0.0
            val roundUp = binding.roundUpSwitch.isChecked
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                binding.optionThirtyPercent.id -> 0.30
                binding.optionTwentyfivePercent.id -> 0.25
                binding.optionTwentyPercent.id -> 0.20
                binding.optionFifteenPercent.id -> 0.15
                binding.optionTenPercent.id -> 0.10
                else -> 0.0
            }
            listener.invoke(costOfService, tipPercentage, roundUp)
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun Switch.roundUp(
    binding: ActivityMainBinding,
    listener: (costOfService: Double, tipPercentage: Double, roundUp: Boolean) -> Unit
) {
    this.setOnCheckedChangeListener { _, roundUp ->
        val costOfService = binding.costOfService.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            binding.optionThirtyPercent.id -> 0.30
            binding.optionTwentyfivePercent.id -> 0.25
            binding.optionTwentyPercent.id -> 0.20
            binding.optionFifteenPercent.id -> 0.15
            binding.optionTenPercent.id -> 0.10
            else -> 0.0
        }
        listener.invoke(costOfService, tipPercentage, roundUp)
    }
}

fun RadioGroup.tipPercentage(
    binding: ActivityMainBinding,
    listener: (costOfService: Double, tipPercentage: Double, roundUp: Boolean) -> Unit
) {
    this.setOnCheckedChangeListener { _, checkedId ->
        val costOfService = binding.costOfService.text.toString().toDoubleOrNull() ?: 0.0
        val roundUp = binding.roundUpSwitch.isChecked
        val tipPercentage = when (checkedId) {
            binding.optionThirtyPercent.id -> 0.30
            binding.optionTwentyfivePercent.id -> 0.25
            binding.optionTwentyPercent.id -> 0.20
            binding.optionFifteenPercent.id -> 0.15
            binding.optionTenPercent.id -> 0.10
            else -> 0.0
        }
        listener.invoke(costOfService, tipPercentage, roundUp)
    }
}
