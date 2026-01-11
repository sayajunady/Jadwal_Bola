package com.kopikode.jadwalbola

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val matchId = intent.getIntExtra("EXTRA_ID", 0)

        val tvScore = findViewById<TextView>(R.id.tvDetailScore)
        val tvStatus = findViewById<TextView>(R.id.tvDetailStatus)
        val tvScorers = findViewById<TextView>(R.id.tvScorers)
        val imgHome = findViewById<ImageView>(R.id.imgDetailHome)
        val imgAway = findViewById<ImageView>(R.id.imgDetailAway)
        val pbLoading = findViewById<ProgressBar>(R.id.pbLoading)

        pbLoading.visibility = View.VISIBLE

        lifecycleScope.launch {
            // Di dalam lifecycleScope.launch DetailActivity
            try {
                val match = RetrofitClient.instance.getMatchDetail(matchId)

                // Tampilkan data dasar dulu (yang sudah berhasil tadi)
                tvScore.text =
                    "${match.score?.fullTime?.home ?: 0} - ${match.score?.fullTime?.away ?: 0}"
                Glide.with(this@DetailActivity).load(match.homeTeam.crest).into(imgHome)
                Glide.with(this@DetailActivity).load(match.awayTeam.crest).into(imgAway)

                // PROSES DAFTAR GOL
                val listGol = StringBuilder()

                // Cek apakah list goals null atau kosong
                if (match.goals.isNullOrEmpty()) {
                    tvScorers.text = "Data pencetak gol tidak tersedia untuk liga ini (API Limit)."
                } else {
                    match.goals.forEach { goal ->
                        // Gunakan simbol bola dan menit
                        val namaPemain = goal.scorer?.name ?: "Pemain Tidak Dikenal"
                        val namaTim = goal.team?.name ?: ""
                        val menit = goal.minute

                        listGol.append("⚽ $menit' - $namaPemain ($namaTim)\n")
                    }
                    tvScorers.text = listGol.toString()
                }

            } catch (e: Exception) {
                Log.e("API_ERROR", "Pesan: ${e.message}")
                tvScorers.text = "Gagal memuat detail pencetak gol."
            }
        }
    }
}