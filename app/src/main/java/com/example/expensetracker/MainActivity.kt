package com.example.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()
        var intent = Intent(this, HomeScreen::class.java)
        Handler(Looper.myLooper()!!).postDelayed({
                startActivity(intent)
                finish()
            }, 2000)
    }
}