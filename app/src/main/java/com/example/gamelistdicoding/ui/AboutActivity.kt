package com.example.gamelistdicoding.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelistdicoding.R
import com.example.gamelistdicoding.adapter.GameAdapter
import com.example.gamelistdicoding.data.GameData
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import android.widget.TextView

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val btnBack: MaterialButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener { finish() }


        val imgProfile: ShapeableImageView = findViewById(R.id.img_profile)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)

        imgProfile.setImageResource(R.drawable.foto_profil)
        tvName.text = "Game List"
        tvDescription.text =
            "Aplikasi ini di buat untuk Submission Akhir di kelas Aplikasi Android Sederhana di dicoding.com. Yang menampilkan 10 daftar game dan dengan fitur utama jika di salah satu item di daftar list di klik menampilkan detail dari game tersebut."
    }
}
