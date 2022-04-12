package com.mobilite.connect4

import android.widget.LinearLayout

class SingleplayerBoard(mainlayout_: LinearLayout, screenWidth: Int, context_: android.content.Context ) :
    Board(
        context_,
        mainlayout_,
        screenWidth
    ) {

    init {
        for (j in 0 until cols) {
            
            val col = LinearLayout(context)
            val lp1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)

            col.layoutParams = lp1
            col.orientation = LinearLayout.VERTICAL
            col.gravity = android.view.Gravity.BOTTOM

            for (col in 0 until cols) {
                val column = mainlayout_.getChildAt(col) as LinearLayout
                column.setOnClickListener {
                    game(col)
                }


            }
        }
    }

    override fun game(col: Int) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }


}