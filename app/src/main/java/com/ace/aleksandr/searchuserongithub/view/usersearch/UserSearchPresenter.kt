package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserSearchPresenter(
    view: UserSearchView
) : BasePresenter<UserSearchView>(view) {


    override fun onCreate() {
    }

    private var disposableGetUserInfo: Disposable? = null

    fun getUserInfo(name: String) {

        if (disposableGetUserInfo != null && disposableGetUserInfo?.isDisposed == false) {
            disposableGetUserInfo?.dispose()
        }

        disposableGetUserInfo = ApiHolder.service.getUserInfo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.isShowLoading(true) }
            .doFinally { view?.isShowLoading(false) }
            .subscribe({
                view?.showUserInfo(it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    override fun onDestroy() {
        disposableGetUserInfo?.dispose()
    }
}