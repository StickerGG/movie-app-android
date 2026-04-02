package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatPassword = binding.etRepeatPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заповни всі поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != repeatPassword) {
                Toast.makeText(this, "Паролі не співпадають", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

            sharedPref.edit()
                .putString("firstName", binding.etFirstName.text.toString())
                .putString("lastName", binding.etLastName.text.toString())
                .putString("login", login)
                .putString("password", password)
                .putBoolean("isAuthorized", true)
                .apply()

            Toast.makeText(this, "Реєстрація успішна", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}