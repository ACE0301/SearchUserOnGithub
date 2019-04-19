package com.ace.aleksandr.searchuserongithub.view.userrepoinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserReposPresenter(
    view: UserReposView,
    private val login: String
) : BasePresenter<UserReposView>(view) {
/*    Применительно к Observable тип Disposable позволяет вызывать метод dispose,
    означающий «Я закончил работать с этим ресурсом, мне больше не нужны данные».
    Если у вас есть сетевой запрос, то он может быть отменён.
    Если вы прослушивали бесконечный поток нажатий кнопок, то это будет означать,
    что вы больше не хотите получать эти события,
    в таком случае можно удалить OnClickListener у View*/
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

        disposableGetUser = ApiHolder.service.getUser(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUser(it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    private fun getUserRepos() {
        if (disposableGetUserRepos != null && disposableGetUserRepos?.isDisposed == false) {
            disposableGetUserRepos?.dispose()
        }

        disposableGetUserRepos = ApiHolder.service.getUserRepos(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserRepos(it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    override fun onDestroy() {
        disposableGetUser?.dispose()
        disposableGetUserRepos?.dispose()
    }

    fun saveRepos() {

    }
}