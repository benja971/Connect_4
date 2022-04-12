package com.mobilite.connect4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Get the singleplayer button
        val singlePlayerButton = findViewById<android.widget.Button>(R.id.singleplayer)
        
        // Get the multiplayer button
        val multiPlayerButton = findViewById<android.widget.Button>(R.id.multiplayer)


        // Set the onClickListener for the singleplayer button
        // singlePlayerButton.setOnClickListener {
        //     // Start the singleplayer activity
        //     startActivity(android.content.Intent(this, ::class.java))
        // }

        // Set the onClickListener for the multiplayer button
        multiPlayerButton.setOnClickListener {
            // Start the multiplayer activity
            startActivity(android.content.Intent(this, MainActivity::class.java))
        }

    }

   
}