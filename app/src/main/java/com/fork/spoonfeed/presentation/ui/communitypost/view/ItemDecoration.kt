package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R

class ItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint().apply {
            color = ContextCompat.getColor(parent.context, R.color.gray02)
        }

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (i != parent.childCount - 1) {

                c.drawRect(
                    child.left.toFloat(),
                    child.bottom.toFloat(),
                    child.right.toFloat(),
                    child.bottom.toFloat() + 1,
                    paint
                )
            }
        }
    }
}
