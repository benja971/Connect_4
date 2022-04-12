package com.mobilite.connect4

import android.widget.LinearLayout

abstract class Board(context_: android.content.Context, mainLayout_: LinearLayout, screenWidth: Int) {
    protected val cols = 7
    protected val rows = 6
    protected val context = context_
    protected val mainLayout = mainLayout_
    protected var board: Array<Array<Int>> = Array(cols) { Array(rows) { 0 } }
    protected val pieceWidth = ((screenWidth / cols) * 0.85).toInt()
    protected val margin = (screenWidth * 0.005).toInt()
    protected var ended = false
    protected var player = 1


    init {

        for (i in 0 until cols) {
            // create a linearlayout
            val ll = LinearLayout(context)

            // set the layout params
            val lp1 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            ll.orientation = LinearLayout.VERTICAL
            ll.layoutParams = lp1
            ll.gravity = android.view.Gravity.BOTTOM

            for (j in 0 until rows) {
                // create a linearlayout
                val piece = LinearLayout(context)

                // set the layout params
                val lp2 = LinearLayout.LayoutParams(pieceWidth, pieceWidth)
                lp2.setMargins(margin, margin, margin, margin)

                piece.layoutParams = lp2
                piece.setBackgroundResource(R.drawable.white_piece)

                // add the piece to the column
                ll.addView(piece)
            }

            mainLayout_.addView(ll)

        }
    }

    abstract fun game(col: Int)

    protected fun hillightWinning(wonList: ArrayList<Pair<Int, Int>>) {
        // Browse the board and highlight the winning pieces
        for (c in 0 until cols) {
            for (r in 0 until rows) {

                if (!wonList.any { ( x, y ) -> x == c && y == r }) {
                    var piece: LinearLayout = mainLayout.getChildAt(c) as LinearLayout
                    piece = piece.getChildAt(r) as LinearLayout
                    piece.alpha = 0.5f
                }

            }
        }
    }

    protected fun update(row: Int, col: Int) {
        val col = mainLayout.getChildAt(col) as LinearLayout
        val piece = col.getChildAt(row) as LinearLayout
        piece.setBackgroundResource(if (player == 1) R.drawable.red_piece else R.drawable.yellow_piece)
    }

    protected fun clear() {
        for (i in 0 until cols)
            for (j in 0 until rows){

                var p: LinearLayout = mainLayout.getChildAt(i) as LinearLayout
                p = p.getChildAt(j) as LinearLayout
                p.alpha = 1f

                board[i][j] = 0
                val col = mainLayout.getChildAt(i) as LinearLayout
                val piece = col.getChildAt(j) as LinearLayout
                piece.setBackgroundResource(R.drawable.white_piece)
            }

    }

    protected abstract fun reset()

    protected fun play(col: Int, player: Int): Int {

        if (this.board[col][0] != 0) {
            return -1
        }

        for (i in rows-1 downTo 0) {
            if (board[col][i] == 0) {
                board[col][i] = player
                return i
            }
        }
        return -1
    }

    protected fun isDraw(): Boolean {
        for (i in 0 until cols)
            for (j in 0 until rows)
                if (board[i][j] == 0)
                    return false
        return true
    }

    private fun checkPlayer(x: Int, y: Int, player: Int): Boolean {
        if (x < 0 || x >= cols || y < 0 || y >= rows)
            return false

        if (board[x][y] != player)
            return false

        return true
    }

    protected fun isWon(player: Int): ArrayList<Pair<Int, Int>> {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                // For all directions
                val directions = arrayOf(Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(1, -1))

                for ((di, dj) in directions) {
                    // Create coords folowing the direction
                    val coords = ArrayList<Pair<Int, Int>>()
                    for (k in 0 until 4) coords.add(Pair(i + k * di, j + k * dj))

                    // Check if all 4 coordinates belong to the player
                    if (coords.all { (x, y) -> checkPlayer(x, y, player) }) return coords

                    // coords.all { (x, y) -> checkPlayer(x, y, player) }
                    // coords.every(({ x, y }) => checkPlayer(x, y, player))
                }
            }
        }

        return ArrayList()
    }


}