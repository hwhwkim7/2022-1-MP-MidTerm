package com.example.a2020316011

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "중간고사"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.calc -> {
                var intent1 = Intent(applicationContext, CalcActivity::class.java)
                startActivity(intent1)
            }
            R.id.profile -> {
                var intent = Intent(applicationContext, ProfileActivity::class.java)
                intent.putExtra("DEPART", mainDept.text.toString())
                intent.putExtra("NUM", mainNum.text.toString())
                intent.putExtra("NAME", mainName.text.toString())
                intent.putExtra("BIRTH", mainBirth.text.toString())
                startActivityForResult(intent, 0)
            }
            R.id.image -> {
                var intent = Intent(applicationContext, ImageActivity::class.java)
                startActivityForResult(intent, 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(data!!.getStringExtra("CHECK") == "P"){
                mainDept.text = data!!.getStringExtra("DEPART")
                mainNum.text = data!!.getStringExtra("NUM")
                mainName.text = data!!.getStringExtra("NAME")
                mainBirth.text = data!!.getStringExtra("BIRTH")
                var layout = data!!.getIntExtra("LAYOUT", 0)
                var color = data!!.getStringExtra("COLOR")

                when(color) {
                    "red" -> mainBase.setBackgroundColor(Color.RED)
                    "green" -> mainBase.setBackgroundColor(Color.GREEN)
                    "blue" -> mainBase.setBackgroundColor(Color.BLUE)
                    "white" -> mainBase.setBackgroundColor(Color.WHITE)
                }

                if (layout == 1) {
                    layout1.visibility = View.VISIBLE
                }
            } else if(data!!.getStringExtra("CHECK") == "I") {
                var ani = data!!.getStringExtra("ANIMAL")
                mainImage.rotation += data!!.getStringExtra("DEGREE").toString().toFloat()
                when(ani) {
                    "cat" -> mainImage.setImageResource(R.drawable.cat)
                    "dog" -> mainImage.setImageResource(R.drawable.dog)
                    "rabbit" -> mainImage.setImageResource(R.drawable.rabbit)
                }
            }
        }
    }
}
