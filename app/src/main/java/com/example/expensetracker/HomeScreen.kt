package com.example.expensetracker

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    private lateinit var cv:ContentValues
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        cv = ContentValues()
        val helper = MyHelper(this)
        val db = helper.readableDatabase
        val rs = db.rawQuery("SELECT SUM(AMOUNT) AS TOTAL FROM expenses", null)
        if (rs.moveToFirst()){
            homeTotalExp.setText("${rs.getInt(0)} Rs")
        }


        floatingActionButton.setOnClickListener{
//            if (rs.moveToFirst()){
//                var total:Int = rs.getInt(0)
//                Toast.makeText(this, "$total", Toast.LENGTH_SHORT).show()
//            }
            val intent = Intent(this, AddExp::class.java)
            startActivity(intent)
        }
        rs.requery()
    }
}