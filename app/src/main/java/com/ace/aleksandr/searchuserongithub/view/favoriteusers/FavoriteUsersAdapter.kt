package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_favorite_users.view.*

class FavoriteUsersAdapter : RecyclerView.Adapter<FavoriteUsersAdapter.ItemHolder>() {
    var onItemClickListener: ((String) -> Unit) = {}
    var onRemoveClick: ((String) -> Unit) = {}

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_users, parent, false))


    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(data[position])
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.ivRemove.setOnClickListener {
                onRemoveClick.invoke(data[adapterPosition])
            }
        }

        fun bindData(item: String) {
            itemView.tvItemFavoriteUsers.apply {
                text = item
                setOnClickListener {
                    onItemClickListener.invoke(item)
                }
            }
        }
    }

}
