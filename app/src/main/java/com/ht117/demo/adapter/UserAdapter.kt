package com.ht117.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ht117.demo.R
import com.ht117.domain.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter: ListAdapter<User, UserAdapter.UserHolder>(differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User?) {
            itemView.tvName.text = user?.name ?: "Default"
            itemView.tvPos.text = user?.position ?: "Position"
        }
    }

    companion object {
        val differ = object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}