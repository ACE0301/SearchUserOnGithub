package com.ace.aleksandr.searchuserongithub.db

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import com.ace.aleksandr.searchuserongithub.model.UserRepository
import io.realm.Realm

interface IUserDBSource {
    fun getUser(login: String): UserRealm

    fun saveUser(user: GithubUser, localLogin: String)

    fun saveRepositories(login: String?, repositories: List<UserRepo>)

    fun getFavoriteUsers(): List<UserRealm>

    fun makeFavorite(login: String)
}

class UserDbSource : IUserDBSource {

    override fun getUser(login: String): UserRealm {
        var user = UserRealm()
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
                    realm.insertOrUpdate(UserRealm().apply
                    {
                        val maxId = realmInstance.where(UserRealm::class.java).max("id")
                        var idRealm = if (maxId == null) 1 else maxId.toInt() + 1
                        id = idRealm
                        login = localLogin
                        name = user.name
                        location = user.location
                    })
                }
            }
//        Realm.getDefaultInstance()
//            .use { realmInstance ->
//                realmInstance.executeTransaction { realm ->
//                    realm.insertOrUpdate(user)
//                }
//            }
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

    override fun getFavoriteUsers(): List<UserRealm> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeFavorite(login: String) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(UserRealm::class.java).equalTo("login", login).findFirst()
                users?.isFavorite = true
            }
        }
    }


//    private var mRealmConfiguration: RealmConfiguration? = null
//    val users: Single<List<UserRealm>>
//        get() {
//            val realm = Realm.getInstance(mRealmConfiguration)
//            val users = realm.copyFromRealm(realm.where(UserRealm::class.java).findAll())
//            realm.close()
//            return Single.just(users)
//        }
//
//    init {
//        //mRealmConfiguration = CustomApplication.getUserConfiguration()
//    }
//
//    fun getUser(id: Long): Single<UserRealm> {
//        val realm = Realm.getInstance(mRealmConfiguration)
//        var user: UserRealm? = null
//        val realmUser = realm.where(UserRealm::class.java).equalTo("id", id).findFirst()
//        if (realmUser != null) {
//            user = realm.copyFromRealm(realmUser)
//        }
//        realm.close()
//        return Single.just(user)
//    }
//
//    fun updateUsers(users: List<UserRealm>): Completable {
//        return Completable.fromAction {
//            val realm = Realm.getInstance(mRealmConfiguration)
//            realm.insertOrUpdate(users)
//            realm.close()
//            Realm.compactRealm(mRealmConfiguration)
//        }
//    }
//
//    fun updateUser(user: UserRealm): Completable {
//        return Completable.fromAction {
//            val realm = Realm.getInstance(mRealmConfiguration)
//            realm.insertOrUpdate(user)
//            realm.close()
//            Realm.compactRealm(mRealmConfiguration)
//        }
//    }

}