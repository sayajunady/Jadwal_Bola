package com.kopikode.jadwalbola

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rvFootball = findViewById<RecyclerView>(R.id.rvFootball)
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getUpcomingMatches()
                // Kirim data hasil API ke Adapter
                val adapter = MatchAdapter(response.matches)
                // Pasang adapter ke RecyclerView
                rvFootball.adapter = adapter
            } catch (e: Exception) {
                Log.e("API_ERROR", e.message.toString())
            }
        }

    }
}