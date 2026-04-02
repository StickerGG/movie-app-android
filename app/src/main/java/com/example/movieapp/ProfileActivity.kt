package com.example.movieapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val tvBirthDate = findViewById<TextView>(R.id.tvBirthDate)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnChangePhoto = findViewById<Button>(R.id.btnChangePhoto)

        imgProfile = findViewById(R.id.imgProfile)

        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        // 🔹 заповнення даних
        etFirstName.setText(sharedPref.getString("firstName", ""))
        etLastName.setText(sharedPref.getString("lastName", ""))
        etEmail.setText(sharedPref.getString("email", ""))
        tvBirthDate.text = sharedPref.getString("birthDate", "Дата народження")

        // 🔹 календар
        tvBirthDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->
                    val date = "$day/${month + 1}/$year"
                    tvBirthDate.text = date
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // 🔥 ВИБІР ФОТО
        btnChangePhoto.setOnClickListener {
            val options = arrayOf("Камера", "Галерея")

            AlertDialog.Builder(this)
                .setTitle("Оберіть джерело")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, 1)
                        }
                        1 -> {
                            val intent = Intent(Intent.ACTION_PICK)
                            intent.type = "image/*"
                            startActivityForResult(intent, 2)
                        }
                    }
                }
                .show()
        }

        // 🔹 збереження
        btnSave.setOnClickListener {
            sharedPref.edit()
                .putString("firstName", etFirstName.text.toString())
                .putString("lastName", etLastName.text.toString())
                .putString("email", etEmail.text.toString())
                .putString("birthDate", tvBirthDate.text.toString())
                .apply()

            Toast.makeText(this, "Збережено", Toast.LENGTH_SHORT).show()
        }
    }

    // 🔥 результат фото
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1 -> {
                    val photo = data?.extras?.get("data") as? Bitmap
                    imgProfile.setImageBitmap(photo)
                }
                2 -> {
                    val uri: Uri? = data?.data
                    imgProfile.setImageURI(uri)
                }
            }
        }
    }
}