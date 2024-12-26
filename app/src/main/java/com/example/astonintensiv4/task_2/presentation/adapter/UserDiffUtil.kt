package com.example.astonintensiv4.task_2.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.astonintensiv4.task_2.domain.model.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}