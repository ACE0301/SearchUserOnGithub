package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.base.BasePresenter


class FavoriteUsersPresenter(view: FavoriteUsersView) : BasePresenter<FavoriteUsersView>(view) {
    override fun onCreate() {
        getUserFromRealm()
    }

    private fun getUserFromRealm() {
        view?.showUsersBookmarks()
    }

    override fun onDestroy() {
        //realm.close()
    }
}