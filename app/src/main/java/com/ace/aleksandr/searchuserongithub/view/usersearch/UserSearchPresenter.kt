package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.model.searchuser.data.UsersPresModel
import com.ace.aleksandr.searchuserongithub.model.searchuser.repository.SearchUserRepository
import com.ace.aleksandr.searchuserongithub.model.searchuser.repository.SearchUserRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserSearchPresenter(
    view: UserSearchView,
    private val searchUserRepository: SearchUserRepository = SearchUserRepositoryImpl()
) : BasePresenter<UserSearchView>(view) {

    override fun onCreate() {}

    private var disposableGetUserInfo: Disposable? = null

    fun getUserInfo(name: String) {
        if (name.isNotEmpty()) {
            disposableGetUserInfo?.dispose()
            disposableGetUserInfo = searchUserRepository.getUserInfo(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.isShowLoading(true) }
                .doFinally { view?.isShowLoading(false) }
                .subscribe({
                    view?.showUserInfo(it)
                }, {
                    view?.showError(it.message ?: "")
                })
        } else view?.showUserInfo(
            UsersPresModel(
                total_count = "Введите значение в поле поиска",
                logins = emptyList()
            )
        )
    }

    override fun onDestroy() {
        disposableGetUserInfo?.dispose()
    }
}