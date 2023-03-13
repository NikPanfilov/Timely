package com.tsu.details.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tsu.details.databinding.GroupItemBinding
import com.tsu.shared.entity.Group

class GroupsAdapter(private val clickListener: (String) -> Unit) : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

	var data: List<Group> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = GroupItemBinding.inflate(inflater, parent, false)

		return GroupsViewHolder(binding)
	}

	override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
		holder.binding.groupNameTextView.text = data[position].name
		holder.binding.root.setOnClickListener { clickListener(data[position].id!!) }
	}

	override fun getItemCount() = data.size

	class GroupsViewHolder(val binding: GroupItemBinding) : RecyclerView.ViewHolder(binding.root)
}