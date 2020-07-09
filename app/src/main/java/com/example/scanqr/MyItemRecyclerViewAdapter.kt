package com.example.scanqr

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.scanqr.network.Employee

class MyItemRecyclerViewAdapter(
    private val values: List<Employee>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        var type : String
        if(item.getType() == 1)
            type = "In"
        else
            type = "Out"
        holder.idView.text = type
        holder.contentView.text = item.getEmployeeName()
        holder.dateView.text = item.getCreatedDate()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_type)
        val contentView: TextView = view.findViewById(R.id.item_name)
        val dateView : TextView = view.findViewById(R.id.item_date)
    }
}