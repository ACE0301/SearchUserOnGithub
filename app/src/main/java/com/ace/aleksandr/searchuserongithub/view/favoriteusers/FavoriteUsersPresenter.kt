package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.model.repository.UsersDataSource
import com.ace.aleksandr.searchuserongithub.model.repository.UsersRepository
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
        disposableGetUserFromRealm?.dispose()
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
    }

    fun onRemoveClick(login: String) {
        disposableRemoveUserFromRealm?.dispose()
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