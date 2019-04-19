package com.ace.aleksandr.searchuserongithub.view.userrepoinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import kotlinx.android.synthetic.main.fragment_repos_user.*

class UserReposFragment : Fragment(), UserReposView {

    companion object {
        const val TAG = "UserReposFragment"

        private const val ARGUMENT_USER_LOGIN = "ARGUMENT_USER_LOGIN"

        fun newInstance(userLogin: String) = UserReposFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_USER_LOGIN, userLogin)
            }
        }
    }

    private val mAdapter = UserReposAdapter()
    private val presenter by lazy { UserReposPresenter(this, arguments?.getString(ARGUMENT_USER_LOGIN) ?: "") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_repos_user, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvUserReposList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        fabSave.setOnClickListener {
            presenter.saveRepos()
        }
    }

    override fun showUser(user: GithubUser) {
        val userInfo = "Имя пользователя ${user.name?:"не указано"}, город:rgge ${user.location?:"не указан"}"
        tvUserLastName.text = userInfo
    }

    override fun showUserRepos(userRepos: List<UserRepo>) {
        mAdapter.data = userRepos.map { it.name ?: "Пустое имя" }
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }
}