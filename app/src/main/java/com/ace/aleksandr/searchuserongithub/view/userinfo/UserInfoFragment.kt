package com.ace.aleksandr.searchuserongithub.view.userinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import com.ace.aleksandr.searchuserongithub.view.favoriteusers.FavoriteUsersFragment
import com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo.FavoriteUserInfoFragment
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : Fragment(), UserInfoView {

    companion object {
        const val TAG = "UserInfoFragment"

        private const val ARGUMENT_USER_LOGIN = "ARGUMENT_USER_LOGIN"

        fun newInstance(userLogin: String) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_USER_LOGIN, userLogin)
            }
        }
    }

    private val mAdapter = UserInfoAdapter()
    private val presenter by lazy { UserReposPresenter(this, arguments?.getString(ARGUMENT_USER_LOGIN) ?: "") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_user_info, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvUserInfoList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        fabSave.setOnClickListener {
            presenter.saveRepos()
            Toast.makeText(this.activity, "Добавлено в закладки", Toast.LENGTH_LONG).show()
        }


        btnToFavoritesFromUserInfo.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.replace(R.id.content, FavoriteUsersFragment.newInstance(FavoriteUserInfoFragment.TAG))
                ?.addToBackStack(FavoriteUserInfoFragment.TAG)
                ?.commit()
        }
    }

    override fun showUser(user: GithubUser) {
        val userInfo = "Имя пользователя ${user.name ?: "не указано"}, город: ${user.location ?: "не указан"}"
        tvUserLastName?.text = userInfo
    }

    override fun showUserRepos(userRepos: List<UserRepo>) {
        mAdapter.data = userRepos.map { it.name ?: "отсутствует" }
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }
}