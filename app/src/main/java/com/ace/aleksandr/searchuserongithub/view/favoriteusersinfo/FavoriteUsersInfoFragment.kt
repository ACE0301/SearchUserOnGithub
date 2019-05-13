package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_repos_user_bookmarks.*


class FavoriteUsersInfoFragment : Fragment(), FavoriteUsersInfoView {


    companion object {
        const val TAG = "FavoriteUsersInfoFragment"

        private const val ARGUMENT_USER_LOGIN = "ARGUMENT_USER_LOGIN"

        fun newInstance(userLogin: String) = FavoriteUsersInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_USER_LOGIN, userLogin)
            }
        }
    }

    private val presenter by lazy { FavoriteUsersInfoPresenter(this, arguments?.getString(ARGUMENT_USER_LOGIN) ?: "") }
    private val mAdapter = FavoriteUsersInfoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_repos_user_bookmarks, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvUserReposListBookmarks.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    override fun showUserInfo(users: RepoRealm) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users =
                    inRealm.where(RepoRealm::class.java!!).equalTo("login", ARGUMENT_USER_LOGIN).findAll()
                //поправить
                //mAdapter.data = users.map { it.login.toString() }
            }
        }
    }

    override fun showUserRepos(userRepos: RepoRealm) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users =
                    inRealm.where(RepoRealm::class.java!!).equalTo("login", ARGUMENT_USER_LOGIN).findAll()
                //поправить
                //mAdapter.data = users.map { it.login.toString() }
            }
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

}