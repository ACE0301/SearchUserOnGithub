package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter

class FavoriteUsersInfoPresenter(
    view: FavoriteUsersInfoView,
    private val login1: String
) : BasePresenter<FavoriteUsersInfoView>(view) {
    override fun onCreate() {
        getReposFromRealm()

    }

    private fun getReposFromRealm() {
//        view?.showUserInfo()
//        view?.showUserRepos()
    }

    override fun onDestroy() {
    }


}