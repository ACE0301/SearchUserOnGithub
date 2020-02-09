package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.view.UserView
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_favorite_users.*

class FavoriteUsersFragment : Fragment(), FavoriteUsersView {

    private var mRealm: Realm? = null

    companion object {
        const val TAG = "FavoriteUsersFragment"
        fun newInstance() = FavoriteUsersFragment()
    }

    private val presenter by lazy { FavoriteUsersPresenter(this) }
    private val mAdapter = FavoriteUsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favorite_users, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFavoriteUsersList.layoutManager = LinearLayoutManager(activity)
        rvFavoriteUsersList.adapter = mAdapter

        mAdapter.onItemClickListener = {
            (activity as? UserView)?.openFavoriteUserInfoFragment(it)
        }
        mAdapter.onRemoveClick = { login ->
            presenter.onRemoveClick(login)
            Toast.makeText(this.context, getString(R.string.deleted), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showFavoriteUsers(users: List<UserRealm>) {
        mAdapter.data = users.map {
            it.login.toString()
        }
    }

    override fun showError(errorText: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm?.close()
    }
}