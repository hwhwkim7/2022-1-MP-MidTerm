package com.example.a2020316011

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        title = "이미지 선택"

        var ani = ""

        radioG.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.cat -> {
                    imageDegree.setImageResource(R.drawable.cat)
                    ani = "cat"
                }
                R.id.dog -> {
                    imageDegree.setImageResource(R.drawable.dog)
                    ani = "dog"
                }
                R.id.rabbit -> {
                    imageDegree.setImageResource(R.drawable.rabbit)
                    ani = "rabbit"
                }
            }
        }

        btnDegree.setOnClickListener {
            imageDegree.rotation += editDegree.text.toString().toFloat()
        }

        returnI.setOnClickListener {
            var intent = Intent(applicationContext, MainActivity::class.java)
            var check = "I"
            intent.putExtra("ANIMAL", ani)
            intent.putExtra("DEGREE", editDegree.text.toString())
            intent.putExtra("CHECK", check)
            setResult(RESULT_OK, intent)
            finish() }

    }
}