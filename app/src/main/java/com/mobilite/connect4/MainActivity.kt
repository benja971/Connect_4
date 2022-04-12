package com.mobilite.connect4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the players names
        val j1 = findViewById<android.widget.EditText>(R.id.player1)
        val j2 = findViewById<android.widget.EditText>(R.id.player2)

        //get the button
        val btn = findViewById<android.widget.Button>(R.id.start_game)

        //set the button on click listener
        btn.setOnClickListener {
            //get the players names
            val name1 = j1.text.toString()
            val name2 = j2.text.toString()

            if(name1.isEmpty() || name2.isEmpty()){
                // Create a toast message
                val toast = android.widget.Toast.makeText(this, "Veuillez entrer vos pseudos", android.widget.Toast.LENGTH_SHORT)
                // Show the toast message
                toast.show()

            }else{
                
                // create the intent
                val intent = Intent(this, MultiplayerActivity::class.java)

                // put the players names in the intent
                intent.putExtra("name1", name1)
                intent.putExtra("name2", name2)

                // start the activity
                startActivity(intent)
            }
        }


    }

}