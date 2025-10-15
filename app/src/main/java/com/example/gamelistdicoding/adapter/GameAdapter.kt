package com.example.gamelistdicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelistdicoding.R
import com.example.gamelistdicoding.data.Game

class GameAdapter(
    private val gameList: List<Game>,
    private val onClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val name: TextView = view.findViewById(R.id.textName)
        val genre: TextView = view.findViewById(R.id.textGenre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gameList[position]
        holder.image.setImageResource(game.image)
        holder.name.text = game.name
        holder.genre.text = game.genre
        holder.itemView.setOnClickListener { onClick(game) }
    }

    override fun getItemCount(): Int = gameList.size
}
