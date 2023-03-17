package com.example.calculator.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.calculator.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class ImcActivity : AppCompatActivity() {

    private var imc:Double = 0.0

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var initialHeight:Int = 120
    private var initialWeight:Int = 50
    private var initialAge:Int = 15

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var tvWeight: TextView
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnMinusWeight: FloatingActionButton

    private lateinit var tvAge: TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnMinusAge: FloatingActionButton

    private lateinit var btnCalculator: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        initComponents()
        initListener()
        initUI()
    }

    private fun calculateIMC(){
        val df = DecimalFormat("#.##")
        imc = initialWeight/(initialHeight.toDouble()/100*initialHeight.toDouble()/100)
        imc = df.format(imc).toDouble()
        Log.i("IMC", "$imc")
    }

    private fun initComponents() {
        viewFemale = findViewById(R.id.viewFemale)
        viewMale = findViewById(R.id.viewMale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)

        tvWeight = findViewById(R.id.tvWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnMinusWeight = findViewById(R.id.btnMinusWeight)

        tvAge = findViewById(R.id.tvAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnMinusAge = findViewById(R.id.btnMinusAge)

        btnCalculator = findViewById(R.id.btnReCalculator)
    }

    private fun initListener() {
        viewFemale.setOnClickListener { setGenderColor(isFemaleSelected) }
        viewMale.setOnClickListener { setGenderColor(isMaleSelected) }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            initialHeight = df.format(value).toInt()
            tvHeight.text = "$initialHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            initialWeight += 1
            setWeight()
        }
        btnMinusWeight.setOnClickListener {
            initialWeight -= 1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            initialAge += 1
            setAge()
        }
        btnMinusAge.setOnClickListener {
            initialAge -= 1
            setAge()
        }
        btnCalculator.setOnClickListener {
            calculateIMC()
            navigateToResult(imc)
        }

    }

    private fun navigateToResult(result:Double) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setAge() {
        tvAge.text = "$initialAge años"
    }

    private fun setWeight() {
        tvWeight.text = "$initialWeight KGS"
    }

    private fun setGenderColor(isViewSelected:Boolean){
        if(isViewSelected){
            viewMale.setCardBackgroundColor(getColor(R.color.background_component_selected))
            viewFemale.setCardBackgroundColor(getColor(R.color.background_component))
        }else{
            viewFemale.setCardBackgroundColor(getColor(R.color.background_component_selected))
            viewMale.setCardBackgroundColor(getColor(R.color.background_component))
        }
    }

    private fun initUI(){
        setWeight()
        setAge()
    }

}