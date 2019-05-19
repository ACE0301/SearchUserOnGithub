package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import android.widget.Toast
import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import com.ace.aleksandr.searchuserongithub.model.UserRealm


class FavoriteUsersPresenter(view: FavoriteUsersView) : BasePresenter<FavoriteUsersView>(view) {
    override fun onCreate() {
        getUserFromRealm()
    }

    private fun getUserFromRealm() {
        //var user = UserDbSource().getUser("login")
        view?.showFavoriteUsers(UserDbSource().getFavoriteUsers())
    }

    fun onRemoveClick(login: String) {
        UserDbSource().deleteFavoriteUser(login)
        view?.showFavoriteUsers(UserDbSource().getFavoriteUsers())
    }

    override fun onDestroy() {
        //realm.close()
    }
}