package com.example.movieapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_edit)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        val items = mutableListOf(
            Item("Елемент 1"),
            Item("Елемент 2"),
            Item("Елемент 3")
        )

        lateinit var adapter: ItemAdapter

        adapter = ItemAdapter(
            items,

            onDelete = { position ->
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
            },

            onEdit = { position ->
                val editText = EditText(this)
                editText.setText(items[position].name)

                AlertDialog.Builder(this)
                    .setTitle("Редагувати")
                    .setView(editText)
                    .setPositiveButton("Зберегти") { _, _ ->
                        val newText = editText.text.toString()
                        if (newText.isNotEmpty()) {
                            items[position].name = newText
                            adapter.notifyItemChanged(position)
                        }
                    }
                    .setNegativeButton("Скасувати", null)
                    .show()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            val editText = EditText(this)

            AlertDialog.Builder(this)
                .setTitle("Новий елемент")
                .setView(editText)
                .setPositiveButton("Додати") { _, _ ->
                    val text = editText.text.toString()
                    if (text.isNotEmpty()) {
                        items.add(Item(text))
                        adapter.notifyItemInserted(items.size - 1)
                    }
                }
                .setNegativeButton("Скасувати", null)
                .show()
        }
    }
}