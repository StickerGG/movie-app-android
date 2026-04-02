package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val login = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()

        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        val savedLogin = sharedPref.getString("login", "")
        val savedPassword = sharedPref.getString("password", "")

        if (login == savedLogin && password == savedPassword) {

            sharedPref.edit().putBoolean("isAuthorized", true).apply()

            Toast.makeText(this, "Вхід успішний", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MenuActivity::class.java))
            finish()

        } else {
            Toast.makeText(this, "Невірний логін або пароль", Toast.LENGTH_SHORT).show()
        }
    }
}