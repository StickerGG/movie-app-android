package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_edit)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val items = listOf(
            Item("Фільм 1"),
            Item("Фільм 2"),
            Item("Фільм 3"),
            Item("Фільм 4")
        )

        val adapter = ItemAdapter(
            items.toMutableList(),

            onDelete = {},

            onEdit = { position ->

                val item = items[position]

                AlertDialog.Builder(this)
                    .setTitle(item.name)
                    .setMessage("Опис для ${item.name}\n\nТут може бути будь-яка інформація.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}