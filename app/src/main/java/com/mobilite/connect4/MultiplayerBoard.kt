package com.mobilite.connect4

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class MultiplayerBoard(context_: android.content.Context, mainLayout_: LinearLayout, screenWidth: Int, name_p1: String, name_p2: String, currentPlayerEditText_: TextView, currentColorLl_: LinearLayout, messageTextView_: TextView) :
    Board(
        context_,
        mainLayout_,
        screenWidth
    ) {

    private val player1 = name_p1
    private val player2 = name_p2
    private val messageTextView = messageTextView_
    private val currentPlayerEditText = currentPlayerEditText_
    private var currentColorLl = currentColorLl_

    init {
        currentPlayerEditText.text = player1

        // Initialize the board with 0 and create the empty board
        for (col in 0 until cols){
            val column = mainLayout_.getChildAt(col) as LinearLayout

            // Add click listener
            column.setOnClickListener {
                game(col)
            }
        }
       
    }


    override fun game(col: Int) {
        if (!ended){
            // Add a piece to the column
            val row = play(col, player)
            
            if (row != -1) {
                // Update the board
                update(row, col)

                val wonList= isWon(player)
                if (wonList.isNotEmpty()) {
                    // End the game
                    ended = true

                    // Update the message
                    messageTextView.text = " a gagné !"

                    // Set the layout user locked
                    mainLayout.isEnabled = false

                    hillightWinning(wonList)
                }

                else if (isDraw()) {
                    // End the game
                    ended = true

                    // Set the layout user locked
                    mainLayout.isEnabled = false

                    hillightWinning(ArrayList())
                    
                    // Update the message
                    currentPlayerEditText.text = ""
                    messageTextView.text = "Match nul !"
                }

                else {
                    // Switch player
                    player = if (player == 1) 2 else 1

                    // Update next player
                    currentPlayerEditText.text = if (player == 1) player1 else player2
                    currentColorLl.background = context.resources.getDrawable(if (player == 1) R.drawable.red_piece else R.drawable.yellow_piece)
                }

            }
            else{
                // Create the toast
                val toast = android.widget.Toast.makeText(context, "Impossible de jouer ici", android.widget.Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    public override fun reset() {
        mainLayout.isEnabled = true
        ended = false
        clear()
        player = 1
        currentPlayerEditText.text = player1
        currentColorLl.background = context.getDrawable(R.drawable.red_piece)
        messageTextView.text = ", a toi de jouer !"

    }

}

