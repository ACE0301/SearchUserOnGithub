package com.ace.aleksandr.searchuserongithub.view.userinfo

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import com.ace.aleksandr.searchuserongithub.model.Repos
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

private var idRealm = 0
private var nameRealm: String? = null
private var locationRealm: String? = null


class UserReposPresenter(
    view: UserInfoView,
    private val login1: String
) : BasePresenter<UserInfoView>(view) {
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

        disposableGetUser = ApiHolder.service.getUser(login1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUser(it)
                saveToRealm(it)
            }, {
                view?.showError(it.message ?: "")

            })
    }

    private fun getUserRepos() {
        if (disposableGetUserRepos != null && disposableGetUserRepos?.isDisposed == false) {
            disposableGetUserRepos?.dispose()
        }
        disposableGetUserRepos = ApiHolder.service.getUserRepos(login1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showUserRepos(it)
                addReposToRealm(it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    private fun saveToRealm(user: GithubUser) {
        Realm.getDefaultInstance()
            .use { realmInstance ->
                realmInstance.executeTransaction { realm ->
                    realm.insertOrUpdate(RepoRealm().apply
                    {
                        id = idRealm++
                        login = login1
                        name = user.name
                        location = user.location
                    })
                }
            }
    }

    private fun addReposToRealm(repos: List<UserRepo>) {

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(RepoRealm::class.java).equalTo("id", idRealm).findFirst()
                realm.insertOrUpdate(RepoRealm().apply {
                    users?.listOfRepos?.addAll(repos.map { Repos(it.name) })
                })
            }
        }
    }

    fun saveRepos() {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(RepoRealm::class.java).equalTo("login", login1).findFirst()
                users?.isFavorite = true
            }
        }
    }


    override fun onDestroy() {
        disposableGetUser?.dispose()
        disposableGetUserRepos?.dispose()
    }
}


