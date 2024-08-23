package com.example.calculatorone

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

        val add = "+"
        val subtract = "-"
        val multiply = "*"
        val divide = "/"
        val porcent = "%"

    var operation = ""
    var numberOne:Double = Double.NaN
    var numberTwo:Double = Double.NaN

    lateinit var temporal:TextView
    lateinit var result:TextView
    lateinit var decimal:DecimalFormat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decimal = DecimalFormat("#.#####")
        temporal = findViewById(R.id.temporal)
        result = findViewById(R.id.result)
    }

    fun changeOperator(b: View) {
        if(temporal.text.isNotEmpty() || numberOne.toString()!="NaN"){

        calculate()
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "÷") {
            operation = "/"
        } else if (boton.text.toString().trim() == "x") {
            operation = "*"
        } else {
            operation = boton.text.toString().trim()
        }
        result.text = decimal.format(numberOne) + operation
        temporal.text = ""

        }
    }

    fun calculate() {
        try {
            if (numberOne.toString()!="NaN") {
                if(temporal.text.toString().isEmpty()){
                    temporal.text = result.text.toString()
                }
                numberTwo = temporal.text.toString().toDouble()
                temporal.text = ""

                when (operation) {
                    "+" -> numberOne = (numberOne + numberTwo)
                    "-" -> numberOne = (numberOne - numberTwo)
                    "*" -> numberOne = (numberOne * numberTwo)
                    "/" -> numberOne = (numberOne / numberTwo)
                    "%" -> numberOne = (numberOne * (numberTwo / 100))
                }
            } else {
                numberOne = temporal.text.toString().toDouble()
            }
        }catch (e:Exception){

        }
    }

    fun selectionNumber(b: View){
        val boton:Button = b as Button
        if(temporal.text.toString()=="0"){
            temporal.text=""
        }
        temporal.text = temporal.text.toString() + boton.text.toString()
    }

    fun equal(b: View){
       calculate()
        result.text = decimal.format(numberOne)
        operation = ""

    }



    fun erased(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "C") {


            if (temporal.text.toString().isNotEmpty()) {
                var actualDates: CharSequence = temporal.text.toString()
                temporal.text = actualDates.subSequence(0, actualDates.length - 1)
            } else {
                numberOne = Double.NaN
                numberTwo = Double.NaN
                temporal.text = ""
                result.text = ""
            }
        } else if (boton.text.toString().trim() == "CA") {
            numberOne = Double.NaN
            numberTwo = Double.NaN
            temporal.text = ""
            result.text = ""
        }
    }

}
