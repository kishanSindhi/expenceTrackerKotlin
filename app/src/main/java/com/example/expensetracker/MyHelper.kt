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
}