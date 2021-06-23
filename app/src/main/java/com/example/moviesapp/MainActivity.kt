package com.example.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.moviesapp.tvSeries.TVSeriesDetailsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.tvSeriesBtn)
        button.setOnClickListener {
            val intent = Intent(this, TVSeriesDetailsActivity::class.java)
            intent.putExtra("tvSeriesId", 84958)
            this.startActivity(intent)
        }
    }
}