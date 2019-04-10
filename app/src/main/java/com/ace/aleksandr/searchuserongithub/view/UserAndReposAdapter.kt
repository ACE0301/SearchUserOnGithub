package com.ace.aleksandr.searchuserongithub.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import kotlinx.android.synthetic.main.item_users_and_repos.view.*

class UserAndReposAdapter(
    val c: Context,
    var listOf: List<String>,
    val onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var v = LayoutInflater.from(c).inflate(R.layout.item_users_and_repos, p0, false)
        return ItemHolder(v, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return listOf.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ItemHolder).bindData(listOf[p1])
    }

    class ItemHolder(itemView: View, var mItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {
            itemView.tvItem.text = item
            itemView.tvItem.setOnClickListener { mItemClickListener.onItemClick(it, adapterPosition) }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}