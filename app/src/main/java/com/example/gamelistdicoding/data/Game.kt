package com.example.gamelistdicoding.data

import java.io.Serializable

data class Game(
    val name: String,
    val genre: String,
    val description: String,
    val image: Int
) : Serializable
