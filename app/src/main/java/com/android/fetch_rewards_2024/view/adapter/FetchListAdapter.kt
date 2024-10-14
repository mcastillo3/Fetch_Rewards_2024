package com.android.fetch_rewards_2024.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.fetch_rewards_2024.R
import com.android.fetch_rewards_2024.model.FetchListModel
import com.android.fetch_rewards_2024.databinding.FetchItemBinding

class FetchListAdapter(
    private val list: List<FetchListModel>
): RecyclerView.Adapter<FetchListAdapter.FetchListViewHolder>(){

    class FetchListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val itemIdTx: TextView = view.findViewById(R.id.itemIdTx)
        val itemListIdTx: TextView = view.findViewById(R.id.itemListIdTx)
        val itemNameTx: TextView = view.findViewById(R.id.itemNameTx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fetch_item, parent, false)

        return FetchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FetchListViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemIdTx.text = itemData.id.toString()
        holder.itemListIdTx.text = itemData.listId.toString()
        holder.itemNameTx.text = itemData.name

    }

    override fun getItemCount() = list.size

}
