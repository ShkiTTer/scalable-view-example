package com.example.scalableview.main

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.scalableview.R
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.databinding.ActivityMainBinding
import com.example.scalableview.list.ListActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind, R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(viewBinding) {
        sizeSeekBar.apply {
            max = SizeType.values().size - 1
            progress = max / 2

            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val size = SizeType.values()[progress]
                    scalableView.size = size
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

                override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
            })
        }

        scalableView.setOnClickListener {
            startActivity(ListActivity.newIntent(this@MainActivity, scalableView.size))
        }
    }
}