package com.example.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.httpjsonparser.Model.Song
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var changingUserName = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiManager = (application as HttpJsonApp).apiManager

        apiManager.getSongList ({ listOfSongs ->
            loading.visibility = View.GONE
            llSong.visibility = View.VISIBLE

            val randPos = Random.nextInt(listOfSongs.songs.size)
            val song = listOfSongs.songs[randPos]

            Picasso.get().load(song.largeImageURL).into(ivAlbumCover)

            tvSongTitle.text = song.title
            tvSongArtist.text = song.artist
            tvUserName.text = (application as HttpJsonApp).username

            displayPlayCount()

            btnPlay.setOnClickListener {
                (application as HttpJsonApp).viewCount++
                displayPlayCount()
            }
            btnChangeUser.setOnClickListener {
                changeUser()
            }
        }, {
            loading.visibility = View.GONE
            tvFailFetch.visibility = View.VISIBLE
        })
    }

    private fun displayPlayCount() {
        val viewCount = (application as HttpJsonApp).viewCount
        var play = " play";
        if (viewCount > 1) {
            play = " plays";
        }
        tvPlayCounter.text = "" + viewCount + play
    }

    private fun changeUser() {
        // clicking on changing user
        if (btnChangeUser.text == getString(R.string.change_user) ) {
            tvUserName.visibility = View.INVISIBLE
            editUserName.visibility = View.VISIBLE
            btnChangeUser.text = getString(R.string.apply)
        } else {
            (application as HttpJsonApp).username = editUserName.text.toString()
            tvUserName.text = (application as HttpJsonApp).username
            btnChangeUser.text = getString(R.string.change_user)
            editUserName.visibility = View.INVISIBLE
            tvUserName.visibility = View.VISIBLE
        }
    }
}
