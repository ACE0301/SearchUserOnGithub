package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.base.disposeIfNotNull
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FavoriteUsersPresenter(view: FavoriteUsersView) : BasePresenter<FavoriteUsersView>(view) {
    var disposableGetUserFromRealm: Disposable? = null
    var disposableRemoveUserFromRealm: Disposable? = null

    override fun onCreate() {
        getUsersFromRealm()
    }

    private fun getUsersFromRealm() {
        disposableGetUserFromRealm.disposeIfNotNull()
        disposableGetUserFromRealm = UserDbSource().getFavoriteUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.showFavoriteUsers(it)
                }, {
                    view?.showError("Ошибка!")
                }

            )
        //UserDbSource().getFavoriteUsers()?.let { view?.showFavoriteUsers(it) }
    }

    fun onRemoveClick(login: String) {
        disposableRemoveUserFromRealm.disposeIfNotNull()
        disposableRemoveUserFromRealm = UserDbSource().deleteFavoriteUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showFavoriteUsers(it)
            }, {
                view?.showError("Ошибка удаления!")
            })
    }

    override fun onDestroy() {
        disposableRemoveUserFromRealm?.dispose()
    }
}