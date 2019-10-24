package com.ace.aleksandr.searchuserongithub.view.userinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.repository.UsersDataSource
import com.ace.aleksandr.searchuserongithub.repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserReposPresenter(
    view: UserInfoView,
    private val localLogin: String,
    private val repository: UsersRepository = UsersDataSource()

) : BasePresenter<UserInfoView>(view) {

    private var disposableGetUser: Disposable? = null
    private var disposableGetUserRepos: Disposable? = null
    private var disposableSaveRepos: Disposable? = null

    init {
        getUser()
        getUserRepos()
    }

    override fun onCreate() {
    }

    private fun getUser() {
        disposableGetUser?.dispose()
        disposableGetUser = ApiHolder.service.getUser(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUser(it)
                repository.saveUser(it, localLogin)
            }, {
                view?.showResult(it.message ?: "")

            })
    }

    private fun getUserRepos() {
        disposableGetUserRepos?.dispose()
        disposableGetUserRepos = ApiHolder.service.getUserRepos(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.isShowLoading(true) }
            .doFinally { view?.isShowLoading(false) }
            .subscribe({
                view?.showUserRepos(it)
                repository.saveRepositories(localLogin, it)
            }, {
                view?.showResult(it.message ?: "Ошибка!")
            })
    }

    fun saveRepos() {
        disposableSaveRepos?.dispose()
        disposableSaveRepos = repository.makeFavorite(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.showResult("Сохранено в закладки!")
                }, {
                    view?.showResult("Ошибка сохранения!")
                }
            )
    }

    override fun onDestroy() {
        disposableGetUser?.dispose()
        disposableGetUserRepos?.dispose()
    }
}


