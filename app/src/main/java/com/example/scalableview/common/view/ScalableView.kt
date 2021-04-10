package com.example.scalableview.common.view

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.example.scalableview.R
import com.example.scalableview.common.extensions.dpToPx
import com.example.scalableview.common.extensions.getScale
import com.example.scalableview.common.extensions.getSize
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.databinding.ViewScalableBinding

class ScalableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding: ViewScalableBinding

    private val defaultIconSize = 60
    private val defaultTextSize = 18

    var size: SizeType = SizeType.MEDIUM
        set(value) {
            if (value == field) return
            field = value

            applySize(value)
        }

    var viewType: ViewType = ViewType.RECTANGLE
        set(value) {
            if (value == field) return
            field = value

            applyViewType(value)
        }

    init {
        inflate(context, R.layout.view_scalable, this).also {
            viewBinding = ViewScalableBinding.bind(it)
        }
    }

    private fun applySize(sizeType: SizeType) = with(viewBinding) {
        val scale = sizeType.getScale()

        TransitionManager.beginDelayedTransition(this@ScalableView)
        appImage.updateLayoutParams {
            width = (defaultIconSize * scale).toInt().dpToPx
            height = (defaultIconSize * scale).toInt().dpToPx
        }

        appText.textSize = defaultTextSize * scale
    }

    private fun applyViewType(viewType: ViewType) {
        val size = viewType.getSize()

        TransitionManager.beginDelayedTransition(this)
        this.updateLayoutParams {
            width = size.first
            height = size.second
        }

    }

    enum class ViewType {
        SQUARE, RECTANGLE
    }
}