package com.mobilite.connect4

import android.text.Html
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView

class Board(nb_cols: Int, nb_rows: Int, context_: android.content.Context, mainLayout_: LinearLayout, screenWidth: Int, name_p1: String, name_p2: String, currentPlayerEditText_: TextView, currentColorLl_: LinearLayout) {
    
    private val cols = nb_cols
    private val rows = nb_rows
    private val context = context_
    private var board = Array(cols) { IntArray(rows) }
    private var player = 1
    private val mainLayout = mainLayout_
    private val pieceWidth = ((screenWidth / cols) * 0.85).toInt()
    private val margin = (screenWidth * 0.005).toInt()
    private val player1 = name_p1
    private val player2 = name_p2
    private val currentPlayerEditText = currentPlayerEditText_
    private var currentColorLl = currentColorLl_

    init {
        Log.i("p4", "Board created")

        currentPlayerEditText.text = player1

        // Initialize the board with 0 and create the empty board
        for (i in 0 until cols){
            // create a linearlayout
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.VERTICAL
            val lp1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            ll.layoutParams = lp1
            ll.gravity = android.view.Gravity.BOTTOM

            // Add click listener
            ll.setOnClickListener {
                Log.i("p4", "Clicked on column $i")
                // Add a piece to the column
                if (play(i, player)) {
                    show()
                    player = if (player == 1) 2 else 1

                    currentPlayerEditText. text = if (player == 1) player1 else player2
                    currentColorLl.background = if (player == 1) context.getDrawable(R.drawable.red_piece) else context.getDrawable(R.drawable.yellow_piece)
                }
                else Log.i("p4", "Impossible de jouer ici")
            }

            for (j in 0 until rows) {
                board[i][j] = 0
                // create a linearlayout
                val piece = LinearLayout(context)
                val lp2 =  LinearLayout.LayoutParams(pieceWidth, pieceWidth)
                lp2.setMargins(margin, margin, margin, margin)
                piece.layoutParams = lp2
                piece.setBackgroundResource(R.drawable.white_piece)
                ll.addView(piece)
            }

            mainLayout.addView(ll)
        }
       
    }

    private fun show() {
        for (i in 0 until cols) {
            // Get the column
            val ll = mainLayout.getChildAt(i) as LinearLayout
            // Clear the layout
            ll.removeAllViews()
            for (j in 0 until rows) {
                // create a linearlayout
                val piece = LinearLayout(context)
                val lp2 =  LinearLayout.LayoutParams(pieceWidth, pieceWidth)
                lp2.setMargins(margin, margin, margin, margin)
                piece.layoutParams = lp2
                Log.i("p4", "piece ok")

                when (board[i][j]) {
                    1 -> piece.setBackgroundResource(R.drawable.red_piece)
                    2 -> piece.setBackgroundResource(R.drawable.yellow_piece)
                    else -> piece.setBackgroundResource(R.drawable.white_piece)
                }

                ll.addView(piece)
            }
        }

    }

    private fun play(col: Int, player: Int): Boolean {
        Log.i("p4", "Player $player played on column $col")

        if (board[col][0] != 0) {
            return false
        }

        for (i in rows-1 downTo 0) {
            if (board[col][i] == 0) {
                board[col][i] = player
                return true
            }
        }
        return false
    }


}

