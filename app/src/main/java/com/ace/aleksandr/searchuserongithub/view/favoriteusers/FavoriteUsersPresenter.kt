package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FavoriteUsersPresenter(view: FavoriteUsersView) : BasePresenter<FavoriteUsersView>(view) {
    var disposable: Disposable? = null

    override fun onCreate() {
        getUserFromRealm()
    }

    private fun getUserFromRealm() {
        view?.showFavoriteUsers(UserDbSource().getFavoriteUsers())
    }

    fun onRemoveClick(login: String) {
        disposable = UserDbSource().deleteFavoriteUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showFavoriteUsers(UserDbSource().getFavoriteUsers())
            }, {
                view?.showError("Ошибка!")
            })
    }

    override fun onDestroy() {
        disposable?.dispose()
    }
}