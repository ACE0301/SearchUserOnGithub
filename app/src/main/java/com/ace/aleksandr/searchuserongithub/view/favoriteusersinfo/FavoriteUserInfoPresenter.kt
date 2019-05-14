package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter

class FavoriteUserInfoPresenter(
    view: FavoriteUserInfoView,
    private val login: String
) : BasePresenter<FavoriteUserInfoView>(view) {
    override fun onCreate() {
        getReposFromRealm(login)
    }

    private fun getReposFromRealm(login: String) {
        view?.apply {
            showUserInfo(login)
            showUserRepos(login)
        }
    }

    override fun onDestroy() {
    }
}