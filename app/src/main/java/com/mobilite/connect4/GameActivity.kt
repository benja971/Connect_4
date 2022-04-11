package com.mobilite.connect4

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Get the screen size
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 30){
            display?.apply {
                getRealMetrics(displayMetrics)
            }
        }else{
            // getMetrics() method was deprecated in api level 30
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        val width = displayMetrics.widthPixels

        // get the player names
        val player1 = intent.getStringExtra("name1").toString()
        val player2 = intent.getStringExtra("name2").toString()

        // Get the board 
        val ll_board = findViewById<LinearLayout>(R.id.board)

        // Get the current player EditTet
        val currentPlayer = findViewById<TextView>(R.id.player_name)

        // Get the current color LinearLayout
        val currentColor = findViewById<LinearLayout>(R.id.current_color)

        val board = Board(7, 6, this, ll_board, width, player1, player2, currentPlayer, currentColor)
    }
}
