package com.kopikode.jadwalbola

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MatchAdapter(private val listMatch: List<Match>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvDate: TextView = view.findViewById(R.id.tvMatchDate)
        val tvHomeName: TextView = view.findViewById(R.id.tvHomeName)
        val tvAwayName: TextView = view.findViewById(R.id.tvAwayName)
        val tvScoreHome: TextView = view.findViewById(R.id.tvScoreHome)
        val tvScoreAway: TextView = view.findViewById(R.id.tvScoreAway)
        val imgHome: ImageView = view.findViewById(R.id.imgHome)
        val imgAway: ImageView = view.findViewById(R.id.imgAway)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = listMatch[position]

        // 1. Set Nama Tim
        holder.tvHomeName.text = match.homeTeam.name
        holder.tvAwayName.text = match.awayTeam.name

        // 2. Set Tanggal (Ambil 10 karakter pertama: YYYY-MM-DD)
        holder.tvDate.text = match.utcDate.take(10)

        // 3. Set Status & Warna
        holder.tvStatus.text = match.status
        if (match.status == "FINISHED") {
            holder.tvStatus.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            holder.tvStatus.setTextColor(Color.GRAY)
        }

        // 4. Set Skor (Menggunakan tanda "-" jika data null)
        val scoreHome = match.score?.fullTime?.home
        val scoreAway = match.score?.fullTime?.away
        holder.tvScoreHome.text = scoreHome?.toString() ?: "-"
        holder.tvScoreAway.text = scoreAway?.toString() ?: "-"

        Glide.with(holder.itemView.context)
            .load(match.homeTeam.crest)
            .placeholder(android.R.drawable.ic_menu_gallery) // Gambar default saat loading
            .error(android.R.drawable.stat_notify_error)    // Gambar jika link rusak
            .into(holder.imgHome)

        Glide.with(holder.itemView.context)
            .load(match.awayTeam.crest)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgAway)
    }

    override fun getItemCount(): Int = listMatch.size
}