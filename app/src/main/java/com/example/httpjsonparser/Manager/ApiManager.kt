package com.example.httpjsonparser.Manager

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.httpjsonparser.Model.ListOfSongs
import com.google.gson.Gson

class ApiManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getSongList(onSuccess: (ListOfSongs) -> Unit, onFail: () -> Unit) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

        val request = StringRequest(
            Request.Method.GET, emailURL, { response ->
                val gson = Gson()
                val listOfSongs = gson.fromJson(response, ListOfSongs::class.java)
                onSuccess(listOfSongs)
            },
            {
                onFail()
            }
        )

        queue.add(request)
    }
}