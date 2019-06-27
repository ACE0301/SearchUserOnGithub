package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.base.disposeIfNotNull
import com.ace.aleksandr.searchuserongithub.repository.UsersDataSource
import com.ace.aleksandr.searchuserongithub.repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteUserInfoPresenter(
    view: FavoriteUserInfoView,
    private val login: String,
    private val repository: UsersRepository = UsersDataSource()
) : BasePresenter<FavoriteUserInfoView>(view) {
    private var disposableGetReposFromRealm: Disposable? = null
    override fun onCreate() {
        getReposFromRealm(login)
    }

    private fun getReposFromRealm(login: String) {
        disposableGetReposFromRealm.disposeIfNotNull()
        disposableGetReposFromRealm = repository.getUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserInfo(it)
            }, {
                view?.showError("Ошибка!")
            })

    }

    override fun onDestroy() {
        disposableGetReposFromRealm?.dispose()
    }
}