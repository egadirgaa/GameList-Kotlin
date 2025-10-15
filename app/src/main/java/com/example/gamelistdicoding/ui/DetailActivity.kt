package com.example.gamelistdicoding.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamelistdicoding.R
import com.example.gamelistdicoding.data.Game

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }


        val imgGame: ImageView = findViewById(R.id.img_detail)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvGenre: TextView = findViewById(R.id.tv_detail_genre)
        val tvDescription: TextView = findViewById(R.id.tv_detail_description)

        val game = intent.getSerializableExtra("game") as? Game

        game?.let {
            imgGame.setImageResource(it.image)
            tvName.text = it.name
            tvGenre.text = it.genre
            tvDescription.text = it.description
        }
    }
}
