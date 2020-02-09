package com.ace.aleksandr.searchuserongithub.view.usersearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserSearchAdapter : RecyclerView.Adapter<UserSearchAdapter.ItemHolder>() {

    var onItemClickListener: ((String) -> Unit) = {}

    private var data = mutableListOf<String>()

    fun setData(data: List<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_search,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(data[position])
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(login: String) {
            itemView.tvItem.apply {
                text = login
                setOnClickListener {
                    onItemClickListener.invoke(login)
                }
            }
        }
    }
}
