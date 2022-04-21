package com.example.expensetracker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_exp.*

class AddExp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exp)
        val methods = arrayOf("Cash", "Google Pay", "Paytm", "Phone Pay", "Others")
        if (addPaymentMethod != null){
            val adapterr = ArrayAdapter(this, android.R.layout.simple_spinner_item, methods)
            addPaymentMethod.adapter = adapterr
        }
        var helper:MyHelper = MyHelper(this)
        var db = helper.readableDatabase
        var cv = ContentValues()
        addTx.setOnClickListener {
            cv.put("TITLE", addTitle.text.toString())
            cv.put("AMOUNT", addAmount.text.toString())
            cv.put("CAT", addPaymentMethod.selectedItem.toString())
            cv.put("DATE", "${addDate.year}/${addDate.month}/${addDate.dayOfMonth}")
            db.insert("expenses", null, cv)

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Record inserted")
            ad.setMessage("Expense added successfully...:)")
            ad.setIcon(android.R.drawable.checkbox_on_background)
            ad.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            })
            val aleartDialog:AlertDialog = ad.create()
            aleartDialog.setCancelable(true)
            aleartDialog.show()
        }
    }
}