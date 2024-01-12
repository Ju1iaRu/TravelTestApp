package com.app.travelactivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.travelactivity.databinding.ActivityAddTravelBinding

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTravelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Добавить путешествие"

        val btnSave = binding.btnSave
        val etTitle = binding.etTitle
        val etDescription = binding.etDescription

        btnSave.setOnClickListener {
            //  код обработки клика на кнопке
        }
    }
}
