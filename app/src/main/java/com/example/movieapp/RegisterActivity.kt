package com.example.movieapp

import android.content.Context
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
            registerUser()
        }
    }

    private fun registerUser() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val login = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()

        // перевірка пустих полів
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
            || login.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()
        ) {
            Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show()
            return
        }

        // перевірка email
        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, "Невірний email", Toast.LENGTH_SHORT).show()
            return
        }

        // паролі співпадають?
        if (password != repeatPassword) {
            Toast.makeText(this, "Паролі не співпадають", Toast.LENGTH_SHORT).show()
            return
        }

        // довжина пароля
        if (password.length < 6) {
            Toast.makeText(this, "Пароль мінімум 6 символів", Toast.LENGTH_SHORT).show()
            return
        }

        // збереження
        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("email", email)
        editor.putString("login", login)
        editor.putString("password", password)

        editor.apply()

        Toast.makeText(this, "Реєстрація успішна", Toast.LENGTH_SHORT).show()
    }
}