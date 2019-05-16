package com.ace.aleksandr.searchuserongithub.view.userinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserReposPresenter(
    view: UserInfoView,
    private val localLogin: String
) : BasePresenter<UserInfoView>(view) {
    /*    Применительно к Observable тип Disposable позволяет вызывать метод dispose,
        означающий «Я закончил работать с этим ресурсом, мне больше не нужны данные».
        Если у вас есть сетевой запрос, то он может быть отменён.
        Если вы прослушивали бесконечный поток нажатий кнопок, то это будет означать,
        что вы больше не хотите получать эти события,
        в таком случае можно удалить OnClickListener у View*/
    private var repositories: List<String>? = null
    private var disposableGetUser: Disposable? = null
    private var disposableGetUserRepos: Disposable? = null


    override fun onCreate() {
        getUser()
        getUserRepos()
    }

    private fun getUser() {
        if (disposableGetUser != null && disposableGetUser?.isDisposed == false) {
            disposableGetUser?.dispose()
        }

        disposableGetUser = ApiHolder.service.getUser(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUser(it)
                UserDbSource().saveUser(it, localLogin)
                //saveToRealm(it)
            }, {
                view?.showError(it.message ?: "")

            })
    }

    private fun getUserRepos() {
        if (disposableGetUserRepos != null && disposableGetUserRepos?.isDisposed == false) {
            disposableGetUserRepos?.dispose()
        }
        disposableGetUserRepos = ApiHolder.service.getUserRepos(localLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserRepos(it)
                UserDbSource().saveRepositories(localLogin, it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    fun saveRepos() {
        UserDbSource().makeFavorite(localLogin)
    }


    override fun onDestroy() {
        disposableGetUser?.dispose()
        disposableGetUserRepos?.dispose()
    }
}


