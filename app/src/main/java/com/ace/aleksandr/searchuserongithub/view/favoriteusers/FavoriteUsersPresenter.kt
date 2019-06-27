package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.base.disposeIfNotNull
import com.ace.aleksandr.searchuserongithub.repository.UsersDataSource
import com.ace.aleksandr.searchuserongithub.repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FavoriteUsersPresenter(
    view: FavoriteUsersView,
    private val repository: UsersRepository = UsersDataSource()
) : BasePresenter<FavoriteUsersView>(view) {

    private var disposableGetUserFromRealm: Disposable? = null
    private var disposableRemoveUserFromRealm: Disposable? = null

    override fun onCreate() {
        getUsersFromRealm()
    }

    private fun getUsersFromRealm() {
        disposableGetUserFromRealm.disposeIfNotNull()
        disposableGetUserFromRealm = repository.getFavoriteUsers()
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
        disposableRemoveUserFromRealm = repository.deleteFavoriteUser(login)
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