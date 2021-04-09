package com.example.scalableview.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.scalableview.R
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.databinding.ActivityListBinding
import com.example.scalableview.list.adapter.ListRecyclerAdapter

class ListActivity : AppCompatActivity(R.layout.activity_list) {

    companion object {
        private const val KEY_SIZE_TYPE = "KEY_SIZE_TYPE"

        fun newIntent(context: Context, sizeType: SizeType) =
            Intent(context, ListActivity::class.java)
                .putExtra(KEY_SIZE_TYPE, sizeType)
    }

    private val sizeType by lazy {
        intent?.getSerializableExtra(KEY_SIZE_TYPE) as? SizeType ?: SizeType.MEDIUM
    }

    private val viewBinding by viewBinding(ActivityListBinding::bind, R.id.mainRecycler)
    private val listAdapter by lazy { ListRecyclerAdapter().apply { setSize(sizeType) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(viewBinding) {
        mainRecycler.apply {
            layoutManager = GridLayoutManager(this@ListActivity, 2)
            adapter = listAdapter
        }
    }
}