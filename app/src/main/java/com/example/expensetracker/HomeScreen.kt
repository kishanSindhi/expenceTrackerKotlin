package com.example.expensetracker

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    private lateinit var cv:ContentValues
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        var expensesList = MyHelper(applicationContext).showExp()
        cv = ContentValues()
        val helper = MyHelper(this)
        val db = helper.readableDatabase
        val rs = db.rawQuery("SELECT SUM(AMOUNT) AS TOTAL FROM expenses", null)
        if (rs.moveToFirst()){
            homeTotalExp.setText("${rs.getInt(0)} Rs")
        }
        floatingActionButton.setOnClickListener{
            val intent = Intent(this, AddExp::class.java)
            startActivity(intent)
        }
        rs.requery()
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        if (expensesList.size > 0){
            recyclerView.visibility = View.VISIBLE
            val adapter = ExpAdapter(applicationContext, expensesList)
            recyclerView.adapter = adapter
        } else{
            Toast.makeText(applicationContext, "No expenses recorded ", Toast.LENGTH_SHORT).show()
        }
    }
}