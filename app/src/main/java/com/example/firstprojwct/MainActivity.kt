package com.example.firstprojwct

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge UI
        setContentView(R.layout.activity_main)

        // Navigate to SecondActive when the "register" TextView is clicked
        val registerTextView: TextView = findViewById(R.id.register)
        registerTextView.setOnClickListener {
            val intent = Intent(this, SecondActive::class.java)
            startActivity(intent)
            finish() // Close MainActivity to prevent going back to it
        }
    }
}
