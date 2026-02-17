package com.app.travelactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.travelactivity.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesHelper = PreferencesHelper(this)

        binding.buttonRegister.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            // Clear previous errors
            binding.usernameInputLayout.error = null
            binding.passwordInputLayout.error = null
            binding.confirmPasswordInputLayout.error = null

            var hasEmptyField = false

            if (username.isEmpty()) {
                binding.usernameInputLayout.error = "The field can't be empty"
                hasEmptyField = true
            }
            if (password.isEmpty()) {
                binding.passwordInputLayout.error = "The field can't be empty"
                hasEmptyField = true
            }
            if (confirmPassword.isEmpty()) {
                binding.confirmPasswordInputLayout.error = "The field can't be empty"
                hasEmptyField = true
            }

            if (hasEmptyField) {
                Log.e("RegistrationActivity", "Registration failed: one or more fields are empty")
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 4) {
                Toast.makeText(this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (preferencesHelper.register(username, password)) {
                Toast.makeText(this, "Registration successful! Please login", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewLogin.setOnClickListener {
            finish()
        }
    }
}
