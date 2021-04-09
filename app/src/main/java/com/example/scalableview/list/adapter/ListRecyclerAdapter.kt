package com.example.scalableview.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scalableview.R
import com.example.scalableview.common.model.SizeType
import com.example.scalableview.common.view.ScalableView

class ListRecyclerAdapter : RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    private var sizeType = SizeType.MEDIUM

    override fun getItemCount(): Int = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = Unit

    fun setSize(sizeType: SizeType) {
        this.sizeType = sizeType
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            (view as ScalableView).size = sizeType
        }
    }
}