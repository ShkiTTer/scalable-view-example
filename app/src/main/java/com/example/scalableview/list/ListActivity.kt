package com.example.scalableview.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.scalableview.R
import com.example.scalableview.common.extensions.getColumns
import com.example.scalableview.common.extensions.getSize
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.common.view.ScalableView
import com.example.scalableview.databinding.ActivityListBinding
import com.example.scalableview.list.adapter.ListRecyclerAdapter

class ListActivity : AppCompatActivity(R.layout.activity_list) {

    companion object {
        private const val KEY_SIZE_TYPE = "KEY_SIZE_TYPE"
        private const val KEY_VIEW_TYPE = "KEY_VIEW_TYPE"

        fun newIntent(context: Context, sizeType: SizeType, viewType: ScalableView.ViewType) =
            Intent(context, ListActivity::class.java)
                .putExtra(KEY_SIZE_TYPE, sizeType)
                .putExtra(KEY_VIEW_TYPE, viewType)
    }

    private val sizeType by lazy {
        intent?.getSerializableExtra(KEY_SIZE_TYPE) as? SizeType ?: SizeType.MEDIUM
    }
    private val viewType by lazy {
        intent?.getSerializableExtra(KEY_VIEW_TYPE) as? ScalableView.ViewType
            ?: ScalableView.ViewType.RECTANGLE
    }

    private val viewBinding by viewBinding(ActivityListBinding::bind, R.id.mainRecycler)
    private val listAdapter by lazy {
        ListRecyclerAdapter().apply {
            setSize(sizeType)
            setItemViewType(viewType)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(viewBinding) {
        mainRecycler.apply {
            layoutManager = GridLayoutManager(this@ListActivity, viewType.getColumns())
            adapter = listAdapter
            addItemDecoration(
                GridDecoration(
                    spanCount = viewType.getColumns(),
                    viewWidthPx = viewType.getSize().first
                )
            )
        }
    }
}