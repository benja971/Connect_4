package com.mobilite.connect4

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MultiplayerActivity : AppCompatActivity() {
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
        val llboard = findViewById<LinearLayout>(R.id.board)

        // Get the current player TextView
        val currentPlayer = findViewById<TextView>(R.id.player_name)

        // Get the message TextView
        val message = findViewById<TextView>(R.id.message)

        // Get the current color LinearLayout
        val currentColor = findViewById<LinearLayout>(R.id.current_color)

        val board = MultiplayerBoard(this, llboard, width, player1, player2, currentPlayer, currentColor, message)

        // Get the restart_game button
        val reset = findViewById<TextView>(R.id.restart_game)
        
        // Set the onClickListener for the restart_game button
        reset.setOnClickListener {
            board.reset()
        }

    }
}
