package com.example.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Item(var name: String)

class ItemAdapter(
    private val items: MutableList<Item>,
    private val onDelete: (Int) -> Unit,
    private val onEdit: (Int) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text = items[position].name

        holder.btnDelete.setOnClickListener {
            onDelete(position)
        }

        holder.itemView.setOnClickListener {
            onEdit(position)
        }
    }
}