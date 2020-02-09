package com.ace.aleksandr.searchuserongithub.view.usersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.searchuser.data.UsersPresModel
import com.ace.aleksandr.searchuserongithub.view.UserView
import kotlinx.android.synthetic.main.fragment_user_search.*


class UserSearchFragment : Fragment(), UserSearchView {

    companion object {
        fun newInstance() = UserSearchFragment()
        const val LOGIN = "LOGIN"
        var login = ""
    }

    private val presenter by lazy { UserSearchPresenter(this) }
    private val mAdapter by lazy { UserSearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState?.getString(LOGIN) != null) {
            login = savedInstanceState.getString(LOGIN).orEmpty()
        }
        return inflater.inflate(R.layout.fragment_user_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (login.isNotEmpty()) etSearch.setText(login)
        rvUsers.layoutManager = LinearLayoutManager(context)
        rvUsers.adapter = mAdapter
        presenter.onCreate()

        btnSearch.setOnClickListener {
            presenter.getUserInfo(etSearch.text.toString())
        }
        mAdapter.onItemClickListener = {
            (activity as? UserView)?.openNewFragment(it)
        }
        btnToFavoritesFromUserSearch.setOnClickListener {
            (activity as? UserView)?.openFavoritesFragment()
        }
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun showUserInfo(userSearchResult: UsersPresModel) {
        tvQuantity.text = userSearchResult.total_count
        mAdapter.setData(userSearchResult.logins)
        rvUsers.scheduleLayoutAnimation()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (etSearch != null) {
            outState.putString(LOGIN, etSearch.text.toString())
        }
    }

    override fun isShowLoading(status: Boolean) {
        if (status) {
            pbLoading.visibility = View.VISIBLE
        } else {
            pbLoading.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }
}