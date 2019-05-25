package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteUserInfoPresenter(
    view: FavoriteUserInfoView,
    private val login: String
) : BasePresenter<FavoriteUserInfoView>(view) {
    var disposable: Disposable? = null
    override fun onCreate() {
        getReposFromRealm(login)
    }

    private fun getReposFromRealm(login: String) {
        //disposable = view?.showUserInfo(UserDbSource().getUser(login))
        disposable = UserDbSource().getUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserInfo(it)
            }, {
                view?.showError("Ошибка!")
            })

    }

    override fun onDestroy() {
        disposable?.dispose()
    }
}