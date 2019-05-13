package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter

class FavoriteUsersInfoPresenter(
    view: FavoriteUsersInfoView,
    private val login1: String
) : BasePresenter<FavoriteUsersInfoView>(view) {
    override fun onCreate() {
        getReposFromRealm(login1)
    }

    private fun getReposFromRealm(login1: String) {
        view?.showUserInfo(login1)
//        view?.showUserRepos()
    }

    override fun onDestroy() {
    }


}