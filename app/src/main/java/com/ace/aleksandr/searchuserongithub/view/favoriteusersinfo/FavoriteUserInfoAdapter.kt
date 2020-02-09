package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_favorite_user_info.view.*

class FavoriteUserInfoAdapter : RecyclerView.Adapter<FavoriteUserInfoAdapter.ItemHolder>() {

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite_user_info,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(data[position])
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {
            itemView.tvItemFavoriteUserInfo.text = item
        }
    }
}