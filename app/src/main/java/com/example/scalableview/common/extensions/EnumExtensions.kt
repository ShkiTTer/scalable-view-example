package com.example.scalableview.common.extensions

import com.example.scalableview.common.model.SizeType
import com.example.scalableview.common.view.ScalableView

fun SizeType.getScale() = when (this) {
    SizeType.SMALL -> 0.8f
    SizeType.SMALL_MEDIUM -> 0.9f
    SizeType.MEDIUM -> 1f
    SizeType.MEDIUM_LARGE -> 1.1f
    SizeType.LARGE -> 1.2f
}

fun ScalableView.ViewType.getSize() = when (this) {
    ScalableView.ViewType.SQUARE -> 100.dpToPx to 100.dpToPx
    ScalableView.ViewType.RECTANGLE -> 150.dpToPx to 200.dpToPx
}

fun ScalableView.ViewType.getColumns() = when (this) {
    ScalableView.ViewType.RECTANGLE -> 2
    ScalableView.ViewType.SQUARE -> 3
}