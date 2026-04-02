package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        val firstName = sharedPref.getString("firstName", "")
        val lastName = sharedPref.getString("lastName", "")

        binding.tvWelcome.text = "Привіт, $firstName $lastName!"

        binding.btnList1.setOnClickListener {
            startActivity(Intent(this, ListEditActivity::class.java))
        }

        binding.btnList2.setOnClickListener {
            startActivity(Intent(this, ListInfoActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            sharedPref.edit().putBoolean("isAuthorized", false).apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}