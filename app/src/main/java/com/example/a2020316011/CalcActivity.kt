package com.example.a2020316011

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calc.*

class CalcActivity : AppCompatActivity() {
    var numo = ""
    var numt = ""
    var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        title = "계산기"

        returnC.setOnClickListener { finish() }
        clear.setOnClickListener {
            equation.text = ""
            result.text = ""
        }

        var btnNum = arrayOf(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9)
        var btnCal = arrayOf(add, sub, mul, div)

        for(btn in btnNum) {
            btn.setOnClickListener {
                numo += btn.text.toString()
                equation.text = equation.text.toString() + btn.text.toString()
            }
        }

        for(btn in btnCal) {
            btn.setOnClickListener {
                operator = btn.text.toString()
                equation.text = equation.text.toString() + btn.text.toString()
                if(numo.isNotEmpty()) {
                    numt = numo
                    numo = ""
                } else {
                    result.text = "오류: 피연산자가 입력되지 않았음"
                }
            }
        }

        answ.setOnClickListener {
            if(numo.isNotEmpty()&&numt.isNotEmpty()) {
                result.text = when(operator) {
                    "+" -> (numt.toInt() + numo.toInt()).toString()
                    "-" -> (numt.toInt() - numo.toInt()).toString()
                    "*" -> (numt.toInt() * numo.toInt()).toString()
                    "/" -> (numt.toInt() / numo.toInt()).toString()
                    else -> ""
                }
                numo = ""
                numt = ""
            } else {
                result.text = "오류: 수식이 완전하지 않음"
            }
        }
    }
}