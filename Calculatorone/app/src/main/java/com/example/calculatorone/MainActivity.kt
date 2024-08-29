package com.example.calculatorone

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

        val add = "+"
        val subtract = "-"
        val multiply = "*"
        val divide = "/"
        val porcent = "%"
        var expression: String = ""


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
        val boton: Button = b as Button
        val operator = when (boton.text.toString().trim()) {
            "÷" -> "/"
            "x" -> "*"
            else -> boton.text.toString().trim()
        }

        expression += operator
        result.text = expression
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

    fun selectionNumber(b: View) {
        val boton: Button = b as Button
        expression += boton.text.toString()
        result.text = expression
    }

    fun equal(b: View) {
        try {

            var expressionWithSqrt = expression.replace("√", "sqrt")

            expressionWithSqrt = expressionWithSqrt.replace(Regex("(\\d+)%")) { matchResult ->
                val number = matchResult.groupValues[1].toDouble()
                (number / 100).toString()
            }


            val expressionEval = ExpressionBuilder(expressionWithSqrt).build()
            val resultValue = expressionEval.evaluate()
            result.text = decimal.format(resultValue)
            expression = resultValue.toString()
        } catch (e: Exception) {
            result.text = "Error"
            expression = ""
        }
    }


    fun erased(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "C") {
            if (expression.isNotEmpty()) {

                expression = expression.substring(0, expression.length - 1)
                result.text = expression
            } else {

                numberOne = Double.NaN
                numberTwo = Double.NaN
                operation = ""
                temporal.text = ""
                result.text = ""
            }
        } else if (boton.text.toString().trim() == "CA") {

            numberOne = Double.NaN
            numberTwo = Double.NaN
            operation = ""
            temporal.text = ""
            result.text = ""
            expression = ""
        }
    }

    fun addParenthesis(b: View) {
        val boton: Button = b as Button
        expression += boton.text.toString()
        result.text = expression
    }


}
