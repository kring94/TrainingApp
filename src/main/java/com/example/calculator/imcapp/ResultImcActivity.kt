package com.example.calculator.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.imcapp.ImcActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {
    private lateinit var tvResultText: TextView
    private lateinit var tvResultNumber: TextView
    private lateinit var tvResultComment: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        tvResultNumber.text = result.toString()
        when(result){
            in 0.00..18.5 -> { // Bajo peso
                tvResultText.text = getString(R.string.low_weight)
                tvResultText.setTextColor(getColor(R.color.low_weight))
                tvResultComment.text = getString(R.string.low_weight_text)
            }
            in 18.51..24.99 -> { // Normal peso
                tvResultText.text = getString(R.string.normal_weight)
                tvResultText.setTextColor(getColor(R.color.normal_weight))
                tvResultComment.text = getString(R.string.normal_weight_text)
            }
            in 25.00..29.99 -> { // Sobrepeso
                tvResultText.text = getString(R.string.over_weight)
                tvResultText.setTextColor(getColor(R.color.over_weight))
                tvResultComment.text = getString(R.string.over_weight_text)
            }
            in 30.00..99.00 -> { // Obesidad
                tvResultText.text = getString(R.string.obesity)
                tvResultText.setTextColor(getColor(R.color.obesity))
                tvResultComment.text = getString(R.string.obesity_text)
            }
            else -> { // Error
                tvResultText.text = getString(R.string.errorImc)
                tvResultText.setTextColor(getColor(R.color.obesity))
                tvResultNumber.text = getString(R.string.errorImc)
                tvResultComment.text = getString(R.string.errorImc)
            }
        }
    }

    private fun initComponents(){
        tvResultComment = findViewById(R.id.tvResultComment)
        tvResultText = findViewById(R.id.tvResultText)
        tvResultNumber = findViewById(R.id.tvResultNumber)
        btnRecalculate = findViewById(R.id.btnReCalculator)
    }
}