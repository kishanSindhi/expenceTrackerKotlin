package com.example.expensetracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context:Context):SQLiteOpenHelper(
    context, "expTracker", null, 1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE expenses(_id integer primary key autoincrement, TITLE TEXT, AMOUNT INTEGER, CAT TEXT, DATE TEXT )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun showExp(): ArrayList<Expenses>{
        val query = "SELECT * FROM expenses"
        val db = this.readableDatabase
        val storeExpenses = ArrayList<Expenses>()
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {

                val title = cursor.getString(1)
                val amt = cursor.getString(2)
                val cat = cursor.getString(3)
                val date = cursor.getString(4)
                storeExpenses.add(Expenses(title, amt, cat, date))
            }
                while (cursor.moveToNext())
        }
        cursor.close()
        return storeExpenses
    }
}