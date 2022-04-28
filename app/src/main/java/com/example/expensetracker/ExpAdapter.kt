package com.example.expensetracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_exp.view.*

class ExpAdapter(private val context: Context, private val expenses:ArrayList<Expenses>):RecyclerView.Adapter<ExpAdapter.ViewHolder>() {
    class ViewHolder(ItemView : View):RecyclerView.ViewHolder(ItemView){
        val amount:TextView = itemView.amount
        val title:TextView = itemView.title
        val date:TextView = itemView.date
        val paymentMethod:TextView = itemView.paymentMethod
        val editBtn:ImageButton = itemView.editBtn
        val deleteBtn:ImageButton = itemView.deleteBtn

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_exp, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense = expenses[position]
        holder.title.text = expense.title
        holder.amount.text = expense.amt
        holder.date.text = expense.date
        holder.paymentMethod.text  = expense.cat
        holder.deleteBtn.setOnClickListener {
            val helper = MyHelper(context)
            val db = helper.readableDatabase
            db.delete("expenses", "title", arrayOf(expense.title))
        }
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}