package com.example.scalableview.common.extensions

import com.example.scalableview.common.model.SizeType

fun SizeType.getScale() = when (this) {
    SizeType.SMALL -> 0.5f
    SizeType.SMALL_MEDIUM -> 0.75f
    SizeType.MEDIUM -> 1f
    SizeType.MEDIUM_LARGE -> 1.25f
    SizeType.LARGE -> 1.5f
}