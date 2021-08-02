package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    // 確保 binding 被使用前已經被初始化
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化 binding 將連結 Views 中的 activity_main.xml
        // 使用 binding 預設會把 layout 定義的內容，轉成 camel case，例如 activity_main.xml => ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 改用 binding 不需要給 R.layout.id 因為已經注入到 binding，只要用 root 就好
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        // 改用 binding，也不用 findViewById 的方式，直接用 binding. 就能拿到 view
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // Note the use of var instead of val.
        // This is because you may need to round up the value if the user selected that option, so the value might change.
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        binding.tipResult.text = getString(R.string.tip_amount, displayTip(tip))
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}