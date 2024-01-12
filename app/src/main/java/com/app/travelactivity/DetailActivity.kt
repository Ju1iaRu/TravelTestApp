package com.app.travelactivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = "Детали путешествий"

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        // данные для отображения подробного описания в макете
//        val titleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val detailTextView: TextView = findViewById(R.id.detailTextView)
        val clickText = intent.getStringExtra("clickText")

//        titleTextView.text = title
        detailTextView.text = clickText
    }
}
