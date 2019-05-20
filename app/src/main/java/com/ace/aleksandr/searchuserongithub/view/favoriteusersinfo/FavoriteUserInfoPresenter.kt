package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.db.UserDbSource

class FavoriteUserInfoPresenter(
    view: FavoriteUserInfoView,
    private val login: String
) : BasePresenter<FavoriteUserInfoView>(view) {
    override fun onCreate() {
        getReposFromRealm(login)
    }

    private fun getReposFromRealm(login: String) {
        view?.apply {
            showUserInfo(UserDbSource().getUser(login))
        }
    }
    override fun onDestroy() {
    }
}