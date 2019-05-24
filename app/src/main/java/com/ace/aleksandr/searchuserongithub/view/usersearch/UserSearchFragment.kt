package com.ace.aleksandr.searchuserongithub.view.usersearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfoSearchResult
import com.ace.aleksandr.searchuserongithub.view.favoriteusers.FavoriteUsersFragment
import com.ace.aleksandr.searchuserongithub.view.userinfo.UserInfoFragment
import kotlinx.android.synthetic.main.fragment_user_search.*

class UserSearchFragment : Fragment(), UserSearchView {

    companion object {
        const val TAG = "UserSearchFragment"

        fun newInstance() = UserSearchFragment()
    }

    private val presenter by lazy { UserSearchPresenter(this) }
    private val mAdapter = UserSearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_user_search, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
        btnSearch.setOnClickListener {
            if (etSearch.text.isEmpty()) {
                etSearch.error = "Логин не может быть пустой!"
            } else {
                presenter.getUserInfo(etSearch.text.toString())
            }
        }
        mAdapter.onItemClickListener = {
            openNewFragment(it)
        }
        pbLoading.isIndeterminate = true

        btnToFavoritesFromUserSearch.setOnClickListener {
            openFavoritesFragment()
        }
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun showUserInfo(userSearchResult: GithubUserInfoSearchResult) {
        val quantityText = "Найдено ${userSearchResult.total_count} пользователей"
        tvQuantity.text = quantityText
        mAdapter.data = userSearchResult.items.map { it.login }
        rvList.scheduleLayoutAnimation()
    }

    private fun openNewFragment(login: String) {
        fragmentManager?.beginTransaction()
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.replace(R.id.content, UserInfoFragment.newInstance(login), UserInfoFragment.TAG)
            ?.addToBackStack(UserInfoFragment.TAG)
            ?.commit()
    }

    private fun openFavoritesFragment() {
        fragmentManager?.beginTransaction()
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.replace(R.id.content, FavoriteUsersFragment.newInstance(FavoriteUsersFragment.TAG))
            ?.addToBackStack(FavoriteUsersFragment.TAG)
            ?.commit()
    }

    override fun isShowLoading(status: Boolean) {
        if (status) {
            pbLoading.visibility = View.VISIBLE
        } else {
            pbLoading.visibility = View.INVISIBLE
        }
    }

}