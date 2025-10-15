package com.example.gamelistdicoding.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelistdicoding.R
import com.example.gamelistdicoding.adapter.GameAdapter
import com.example.gamelistdicoding.data.GameData
import android.view.animation.AnimationUtils

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.applySavedTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        val gameList = GameData.gameList

        gameAdapter = GameAdapter(gameList) { selectedGame ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("game", selectedGame)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = gameAdapter

        val layoutAnimationController = AnimationUtils.loadLayoutAnimation(
            this, R.anim.layout_animation_fall_down
        )
        recyclerView.layoutAnimation = layoutAnimationController
        recyclerView.scheduleLayoutAnimation()

        val menuButton: ImageButton = findViewById(R.id.btn_menu)
        menuButton.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.menu_main, popup.menu)
            popup.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_dark_mode -> {
                        ThemeUtils.toggleTheme(this)
                        recreate()
                        true
                    }
                    R.id.menu_about -> {
                        val intent = Intent(this, AboutActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}
