package com.example.tugaschapter2.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tugaschapter2.R
import com.example.tugaschapter2.databinding.ActivityMainBinding
import com.example.tugaschapter2.common.costOfService
import com.example.tugaschapter2.common.roundUp
import com.example.tugaschapter2.common.tipPercentage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindView()
        bindObserver()
    }

    private fun bindView() {
        binding.imageView.setImageResource(R.drawable.opm)
        intercationButtonClickUI()
        interactionEditTextRealtimeUI()
        interactionSwitchRealtimeUI()
        interactionRadioGroupRealtimeUI()
    }

    private fun bindObserver() {
        lifecycleScope.launch {
            viewModel.tipAmount.collect { tipAmount ->
                binding.tipResult.text = "Tip Amount: $tipAmount"
            }
        }
    }

    private fun intercationButtonClickUI() {
        binding.btnCalculate.setOnClickListener {
            val costOfService = binding.costOfService.text.toString().toDoubleOrNull() ?: 0.0
            val roundUp = binding.roundUpSwitch.isChecked
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                binding.optionThirtyPercent.id -> 0.30
                binding.optionTwentyfivePercent.id -> 0.25
                binding.optionTwentyPercent.id -> 0.20
                binding.optionFifteenPercent.id -> 0.15
                binding.optionTenPercent.id -> 0.10
                else -> 0.0
            }
            viewModel.calculateTip(costOfService, tipPercentage, roundUp)
        }
    }

    private fun interactionEditTextRealtimeUI() {
        binding.costOfService.costOfService(binding) { costOfService, tipPercentage, roundUp ->
            viewModel.calculateTip(costOfService, tipPercentage, roundUp)
        }
    }

    private fun interactionSwitchRealtimeUI() {
        binding.roundUpSwitch.roundUp(binding) { costOfService, tipPercentage, roundUp ->
            viewModel.calculateTip(costOfService, tipPercentage, roundUp)
        }
    }

    private fun interactionRadioGroupRealtimeUI() {
        binding.tipOptions.tipPercentage(binding) { costOfService, tipPercentage, roundUp ->
            viewModel.calculateTip(costOfService, tipPercentage, roundUp)
        }
    }
}
