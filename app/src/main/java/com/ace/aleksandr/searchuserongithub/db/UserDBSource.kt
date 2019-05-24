package com.ace.aleksandr.searchuserongithub.db

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import com.ace.aleksandr.searchuserongithub.model.UserRepository
import io.realm.Realm
import io.realm.RealmResults


interface IUserDBSource {
    fun getUser(login: String): UserRealm

    fun saveUser(user: GithubUser, localLogin: String)

    fun saveRepositories(login: String?, repositories: List<UserRepo>)

    fun getFavoriteUsers(): RealmResults<UserRealm>

    fun deleteFavoriteUser(login: String)

    fun makeFavorite(login: String)
}

class UserDbSource : IUserDBSource {

    override fun deleteFavoriteUser(login: String) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                val rows = realm.where(UserRealm::class.java).equalTo("login", login).findAll()
                rows.deleteFirstFromRealm()
            }
        }
    }

    override fun getUser(login: String): UserRealm {
        lateinit var user: UserRealm
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                user = inRealm.where(UserRealm::class.java).equalTo("login", login).findFirst()
                    ?: UserRealm()
            }
        }
        return user
    }

    override fun saveUser(user: GithubUser, localLogin: String) {
        Realm.getDefaultInstance()
            .use { realmInstance ->
                realmInstance.executeTransaction { realm ->
                    val thisUser = realm.where(UserRealm::class.java)
                        .equalTo("login", localLogin).findFirst()
                    realm.insertOrUpdate(UserRealm().apply
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
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(UserRealm::class.java).equalTo("login", login).findFirst()
                realm.insertOrUpdate(UserRealm().apply {
                    users?.listOfRepos?.addAll(repositories.map { UserRepository(it.name) })
                })
            }
        }
    }

    override fun getFavoriteUsers(): RealmResults<UserRealm> {
        lateinit var users: RealmResults<UserRealm>
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                users = inRealm.where(UserRealm::class.java).equalTo("isFavorite", true).findAll()
            }
        }
        return users
    }

    override fun makeFavorite(login: String) {
        Realm.getDefaultInstance().use { realm ->
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