package com.example.scalableview.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.scalableview.R
import com.example.scalableview.common.extensions.getScale
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.databinding.ViewScalableBinding

class ScalableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val viewBinding: ViewScalableBinding

    var size: SizeType = SizeType.MEDIUM
        set(value) {
            if (value == field) return
            field = value

            applySize(value)
        }

    init {
        inflate(context, R.layout.view_scalable, this).also {
            viewBinding = ViewScalableBinding.bind(it)
        }
    }

    private fun applySize(sizeType: SizeType) {
        val scale = sizeType.getScale()
        this.animate().scaleX(scale).scaleY(scale).start()
    }
}