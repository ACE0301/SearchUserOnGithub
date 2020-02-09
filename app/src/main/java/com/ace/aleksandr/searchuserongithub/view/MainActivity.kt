package com.ace.aleksandr.searchuserongithub.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.view.favoriteusers.FavoriteUsersFragment
import com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo.FavoriteUserInfoFragment
import com.ace.aleksandr.searchuserongithub.view.userinfo.UserInfoFragment
import com.ace.aleksandr.searchuserongithub.view.usersearch.UserSearchFragment


class MainActivity : AppCompatActivity(), UserView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSearchFragment()
    }

    private fun showSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserSearchFragment.newInstance())
            .commit()
    }

    override fun openFavoritesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FavoriteUsersFragment.newInstance()
            )
            .addToBackStack(FavoriteUsersFragment.TAG)
            .commit()

    }

    override fun openNewFragment(login: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                UserInfoFragment.newInstance(login)
            )
            .addToBackStack(UserInfoFragment.TAG)
            .commit()
    }

    override fun openFavoriteUserInfoFragment(login: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FavoriteUserInfoFragment.newInstance(login),
                FavoriteUserInfoFragment.TAG
            )
            .addToBackStack(FavoriteUserInfoFragment.TAG)
            .commit()
    }
}