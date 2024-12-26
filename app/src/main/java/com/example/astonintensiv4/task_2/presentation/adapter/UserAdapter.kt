package com.example.astonintensiv4.task_2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.astonintensiv4.databinding.ItemUserBinding
import com.example.astonintensiv4.task_2.domain.model.User

class UserAdapter : ListAdapter<User, UserViewHolder>(UserDiffUtil()) {
    private var onClickUserListener: ((User, Int) -> Unit)? = null

    fun setOnClickUserListener(listener: (User, Int) -> Unit) {
        onClickUserListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(viewBinding).apply {
            viewBinding.root.setOnClickListener {
                val user = currentList[adapterPosition]
                onClickUserListener?.invoke(user, adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding) {
            val context = holder.itemView.context
            userPhoto.setImageDrawable(ContextCompat.getDrawable(context, user.image.toInt()))
            userName.text = user.name
            userSurname.text = user.surName
            userPhoneNumber.text = user.phone
        }
    }
}

class UserViewHolder(
    val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root)