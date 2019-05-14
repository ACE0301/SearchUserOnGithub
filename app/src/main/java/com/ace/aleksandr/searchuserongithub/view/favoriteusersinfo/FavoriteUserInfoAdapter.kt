package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_favorite_user_info.view.*

class FavoriteUserInfoAdapter : RecyclerView.Adapter<FavoriteUserInfoAdapter.ItemHolder>() {

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemHolder =
        ItemHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_favorite_user_info, p0, false))

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(data[position])
    }


    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {
            itemView.tvItemFavoriteUserInfo.text = item
        }

    }
}