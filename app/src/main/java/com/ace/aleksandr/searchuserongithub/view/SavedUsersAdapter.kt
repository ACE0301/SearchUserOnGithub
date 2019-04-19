package com.ace.aleksandr.searchuserongithub.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.db.migration.models.RepoRealm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_user_repos.view.*

class SavedUsersAdapter(
    //val c: Context,
    //val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RealmChangeListener<RepoRealm> {
    lateinit var users: RealmResults<RepoRealm>

    fun SavedUsersAdapter(books: RealmResults<RepoRealm>) {
        users = books
        //users.addChangeListener(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_user_repos, parent, false)
        return SavedUsersAdapter.ItemHolder(v)
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as SavedUsersAdapter.ItemHolder).bindData(users.get(p1)?.nameofUser ?: "")
    }

    override fun getItemCount(): Int {
        return users?.size!!
    }


    override fun onChange(t: RepoRealm) {
        notifyDataSetChanged()
    }

    class ItemHolder(itemView: View):
                     //var mItemClickListener: SavedUsersAdapter.OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String) {
            itemView.tvItem.text = item
            //itemView.tvItem.setOnClickListener { mItemClickListener.onItemClick(it, adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}