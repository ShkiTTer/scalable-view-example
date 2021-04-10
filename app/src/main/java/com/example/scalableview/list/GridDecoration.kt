package com.example.scalableview.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridDecoration(private val spanCount: Int, private val viewWidthPx: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // initially element positioned at the left side of a cell
        // we should move it right
        super.getItemOffsets(outRect, view, parent, state)

        // get position in row
        val position = parent.getChildAdapterPosition(view) % spanCount

        // calculate total free space between elements
        val parentWidth = parent.width
        val totalSpacingSize = parentWidth - (viewWidthPx * spanCount)

        // calculate spacing size between each element
        val spacingSize = totalSpacingSize / (spanCount + 1)
        val spacingHalfSize = spacingSize / 2

        // space to move from left depends on element position in a row
        val spacingMultiplier = (spanCount.toFloat() - position) / spanCount
        val leftSpacing = (spacingSize * spacingMultiplier).toInt()

        outRect.set(leftSpacing, spacingHalfSize, leftSpacing, spacingHalfSize)
    }
}