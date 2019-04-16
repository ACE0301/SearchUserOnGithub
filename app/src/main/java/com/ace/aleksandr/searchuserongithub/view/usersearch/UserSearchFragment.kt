package com.ace.aleksandr.searchuserongithub.view.usersearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfo
import com.ace.aleksandr.searchuserongithub.view.UserSearchAdapter
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
            presenter.getUserInfo(etSearch.text.toString())
        }
        mAdapter.onItemClickListener = {
            openNewFragment(it)
        }
        pbLoading.isIndeterminate = true
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun showUserInfo(user: GithubUserInfo) {
        val quantityText = "Найдено ${user.total_count} пользователей"
        tvQuantity.text = quantityText
        mAdapter.data = user.items.map { it.login }
        rvList.scheduleLayoutAnimation()
    }

    private fun openNewFragment(userName: String) {
        Toast.makeText(activity, "Открываем новый экран $userName", Toast.LENGTH_LONG).show()
    }

    override fun isShowLoading(status: Boolean) {
        if (status) {
            pbLoading.visibility = View.VISIBLE
        } else {
            pbLoading.visibility = View.INVISIBLE
        }
    }
}