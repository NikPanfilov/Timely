package com.tsu.scheduleselection.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tsu.scheduleselection.databinding.ItemBinding
import com.tsu.scheduleselection.domain.entity.ListItem

class SearchAdapter(private val clickListener: (String) -> Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

	var data: List<ListItem> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemBinding.inflate(inflater, parent, false)

		return SearchViewHolder(binding)
	}

	override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
		holder.binding.itemTextView.text = data[position].name
		holder.binding.root.setOnClickListener { clickListener(data[position].name) }
	}

	override fun getItemCount() = data.size

	class SearchViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}