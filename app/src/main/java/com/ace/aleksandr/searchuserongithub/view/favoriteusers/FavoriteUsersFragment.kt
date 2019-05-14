package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo.FavoriteUserInfoFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_favorite_users.*

class FavoriteUsersFragment : Fragment(), FavoriteUsersView {
    private var mRealm: Realm? = null

    companion object {
        const val TAG = "FavoriteUsersFragment"

        fun newInstance(tag: String) = FavoriteUsersFragment()

    }

    private val presenter by lazy { FavoriteUsersPresenter(this) }
    private val mAdapter = FavoriteUsersAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_favorite_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFavoriteUsersList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
        mAdapter.onItemClickListener = {
            openNewFragment(it)
        }
    }


    override fun showFavoriteUsers() {

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users =
                    inRealm.where(RepoRealm::class.java!!).equalTo("isFavorite", true).findAll()
                mAdapter.data = users.map { it.login.toString() }
            }
        }
    }

    private fun openNewFragment(login: String) {
        fragmentManager?.beginTransaction()
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.replace(R.id.content, FavoriteUserInfoFragment.newInstance(login), FavoriteUserInfoFragment.TAG)
            ?.addToBackStack(FavoriteUserInfoFragment.TAG)
            ?.commit()
    }

    override fun showError(errorText: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm?.close()
    }
}