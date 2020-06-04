package com.example.httpjsonparser.Model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val durationMillis: Int,
    val smallImageURL: String,
    val largeImageURL: String
)