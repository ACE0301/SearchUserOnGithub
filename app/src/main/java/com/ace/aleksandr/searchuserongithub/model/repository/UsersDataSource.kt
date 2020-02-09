package com.ace.aleksandr.searchuserongithub.model.repository

import com.ace.aleksandr.searchuserongithub.base.realm.RealmProvider
import com.ace.aleksandr.searchuserongithub.base.realm.createRealmProvider
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import com.ace.aleksandr.searchuserongithub.model.UserRepository
import io.reactivex.Completable
import io.reactivex.Single

class UsersDataSource(private val realmProvider: RealmProvider = createRealmProvider()) : UsersRepository {
    override fun getUser(login: String): Single<UserRealm> {
        lateinit var user: UserRealm

        realmProvider.realm.use { realm ->
            realm.executeTransaction { inRealm ->
                user = inRealm.where(UserRealm::class.java).equalTo("login", login).findFirst()
                    ?: UserRealm()
            }
        }
        return Single.just(user)
    }

    override fun deleteFavoriteUser(login: String): Single<List<UserRealm>> {
        var users: List<UserRealm>? = null
        realmProvider.realm.use { realm ->
            realm.executeTransaction {
                val rows = realm.where(UserRealm::class.java).equalTo("login", login).findAll()
                rows.deleteFirstFromRealm()
                users = realm.where(UserRealm::class.java).equalTo("isFavorite", true).findAll()

            }
            return Single.just(users)
        }
    }


    override fun saveUser(user: GithubUser, localLogin: String) {
        realmProvider.realm.use { realmInstance ->
            realmInstance.executeTransaction { realm ->
                val thisUser = realm.where(UserRealm::class.java)
                    .equalTo("login", localLogin).findFirst()
                realm.insertOrUpdate(
                    UserRealm().apply
                    {
                        login = localLogin
                        name = user.name
                        location = user.location
                        isFavorite = thisUser?.isFavorite ?: false
                    })
            }
        }
    }

    override fun saveRepositories(login: String?, repositories: List<UserRepo>) {
        realmProvider.realm.use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(UserRealm::class.java).equalTo("login", login).findFirst()
                realm.insertOrUpdate(UserRealm().apply {
                    users?.listOfRepos?.addAll(repositories.map { UserRepository(it.name) })
                })
            }
        }
    }

    override fun getFavoriteUsers(): Single<List<UserRealm>> {
        var users: List<UserRealm>? = null
        realmProvider.realm.use { realm ->
            realm.executeTransaction { inRealm ->
                users = inRealm.where(UserRealm::class.java).equalTo("isFavorite", true).findAll()
            }
        }
        return Single.just(users)
    }

    override fun makeFavorite(login: String) = Completable.fromAction {
        realmProvider.realm.use { realm ->
            realm.executeTransaction { inRealm ->
                inRealm.where(UserRealm::class.java)
                    .equalTo(UserRealm.LOGIN, login)
                    .findFirst()?.let {
                        it.isFavorite = true
                    }
            }
        }
    }
}
