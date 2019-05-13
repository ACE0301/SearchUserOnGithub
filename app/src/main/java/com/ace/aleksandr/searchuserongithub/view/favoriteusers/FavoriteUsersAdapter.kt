package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_users_in_bookmarks.view.*

class FavoriteUsersAdapter : RecyclerView.Adapter<FavoriteUsersAdapter.UserBookmarksHolder>() {
    var onItemClickListener: ((String) -> Unit) = {}

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBookmarksHolder =
        UserBookmarksHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_users_in_bookmarks,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserBookmarksHolder, position: Int) {
        holder.bindData(data[position])
    }

    inner class UserBookmarksHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {
            itemView.tvItemBookmarks.text = item
            itemView.tvItemBookmarks.setOnClickListener {
                onItemClickListener.invoke(item)
            }
        }
    }
}
