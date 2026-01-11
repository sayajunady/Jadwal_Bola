package com.kopikode.jadwalbola

import android.content.Intent
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

/**
 * MainActivity is the primary entry point of the Jadwal Bola application.
 * It displays a list of upcoming football matches fetched from a remote API.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Initializes the activity, configures the UI, and initiates the data fetching process.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in [onSaveInstanceState]. Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rvFootball = findViewById<RecyclerView>(R.id.rvFootball)

        // Launch a coroutine to fetch data from the API asynchronously
        // In MainActivity.kt
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getUpcomingMatches()

                // Initialize the adapter once
                val adapter = MatchAdapter(response.matches) { match ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("EXTRA_ID", match.id) // <-- Error is here
                    startActivity(intent)
                }
                rvFootball.adapter = adapter
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching matches", e)
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
