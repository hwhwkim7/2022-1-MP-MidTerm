package com.example.a2020316011

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.birthP
import kotlinx.android.synthetic.main.dialog.view.*
import java.util.*

class ProfileActivity : AppCompatActivity() {
    var color = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        title = "개인 정보 입력"

        var layout = 1

        registerForContextMenu(base)

        returnP.setOnClickListener {
            if(birthEdit.text.toString().isNotEmpty()) {
                layout = 1
            } else layout = 0
            var intent = Intent(applicationContext, MainActivity::class.java)
            var check = "P"
            intent.putExtra("DEPART", deptEdit.text.toString())
            intent.putExtra("NUM", numEdit.text.toString())
            intent.putExtra("NAME", nameEdit.text.toString())
            intent.putExtra("BIRTH", birthEdit.text.toString())
            intent.putExtra("LAYOUT", layout)
            intent.putExtra("COLOR", color)
            intent.putExtra("CHECK", check)
            setResult(RESULT_OK, intent)
            finish() }

        deptEdit.text = intent.getStringExtra("DEPART")
        numEdit.text = intent.getStringExtra("NUM")
        nameEdit.text = intent.getStringExtra("NAME")
        birthEdit.text = intent.getStringExtra("BIRTH")


        deptP.setOnClickListener {
            var dlg = AlertDialog.Builder(this@ProfileActivity)
            var dlgView = View.inflate(this@ProfileActivity, R.layout.dialog, null)
            dlg.setTitle("개인정보 수정")
            dlg.setView(dlgView)
            dlgView.dlgTv.text = "학과: "
            dlg.setPositiveButton("확인") { dialog, which ->
                deptEdit.text = dlgView.dlgEdit.text.toString()
            }
            dlg.show()
        }

        numP.setOnClickListener {
            var dlg = AlertDialog.Builder(this@ProfileActivity)
            var dlgView = View.inflate(this@ProfileActivity, R.layout.dialog, null)
            dlg.setTitle("개인정보 수정")
            dlg.setView(dlgView)
            dlgView.dlgTv.text = "학번: "
            dlg.setPositiveButton("확인") { dialog, which ->
                numEdit.text = dlgView.dlgEdit.text.toString()
            }
            dlg.show()
        }

        nameP.setOnClickListener {
            var dlg = AlertDialog.Builder(this@ProfileActivity)
            var dlgView = View.inflate(this@ProfileActivity, R.layout.dialog, null)
            dlg.setTitle("개인정보 수정")
            dlg.setView(dlgView)
            dlgView.dlgTv.text = "이름: "
            dlg.setPositiveButton("확인") { dialog, which ->
                nameEdit.text = dlgView.dlgEdit.text.toString()
            }
            dlg.show()
        }

        birthP.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
                birthEdit.text = "${year}년 ${month+1}월 ${dayofMonth}일"
            }
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
        menu!!.setHeaderTitle("배경색 변경")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        color = when(item.itemId) {
                    R.id.red -> "red"
                    R.id.green -> "green"
                    R.id.blue -> "blue"
                    R.id.white -> "white"
                    else -> ""
                }
        return super.onContextItemSelected(item)
    }
}