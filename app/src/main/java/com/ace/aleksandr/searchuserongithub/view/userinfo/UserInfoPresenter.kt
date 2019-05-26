package com.ace.aleksandr.searchuserongithub.view.userinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.base.disposeIfNotNull
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserReposPresenter(
    view: UserInfoView,
    private val localLogin: String
) : BasePresenter<UserInfoView>(view) {

    //private var repositories: List<String>? = null
    private var disposableGetUser: Disposable? = null
    private var disposableGetUserRepos: Disposable? = null
    private var disposableSaveRepos: Disposable? = null


    override fun onCreate() {
        getUser()
        getUserRepos()
    }

    private fun getUser() {
        disposableGetUser.disposeIfNotNull()
        disposableGetUser = ApiHolder.service.getUser(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUser(it)
                UserDbSource().saveUser(it, localLogin)
            }, {
                view?.showResult(it.message ?: "")

            })
    }

    private fun getUserRepos() {
        disposableGetUserRepos.disposeIfNotNull()
        disposableGetUserRepos = ApiHolder.service.getUserRepos(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserRepos(it)
                UserDbSource().saveRepositories(localLogin, it)
            }, {
                view?.showResult(it.message ?: "Ошибка!")
            })
    }

    fun saveRepos() {
        disposableSaveRepos.disposeIfNotNull()
        disposableSaveRepos = UserDbSource().makeFavorite(localLogin)
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


