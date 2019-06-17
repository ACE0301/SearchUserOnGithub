package com.ace.aleksandr.searchuserongithub.view.userinfo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserInfoAdapter : RecyclerView.Adapter<UserInfoAdapter.ItemHolder>() {

    var onItemClickListener: ((String) -> Unit) = {}

    var data: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false))


    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(data[position])
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {

            itemView.tvItem.apply {
                text = item
                setOnClickListener {
                    onItemClickListener.invoke(item)
                }
            }
        }
    }
}