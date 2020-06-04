package com.example.httpjsonparser.Model

data class ListOfSongs(
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)