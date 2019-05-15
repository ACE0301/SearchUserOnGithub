package com.ace.aleksandr.searchuserongithub.db

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.realm.Realm
import io.realm.RealmList

interface IUserDBSource {
    fun getUser(login: String): UserRealm

    fun saveUser(user: UserRealm)

    fun saveRepositories(login: String?, repositories: List<String?>)

    fun getFavoriteUsers(): List<UserRealm>

    fun makeFavorite(login: String)
}

class UserDbSource : IUserDBSource {
    override fun getUser(login: String): UserRealm {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: UserRealm) {
        Realm.getDefaultInstance()
            .use { realmInstance ->
                realmInstance.executeTransaction { realm ->
                    realm.insertOrUpdate(user)
                }
            }
    }

    override fun saveRepositories(login: String?, repositories: List<String?>) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                realm.where(UserRealm::class.java)
                    .equalTo("login", login)
                    .findFirst().let {
                        var user = it
                        //user?.listOfRepos = repositories
                        inRealm.insertOrUpdate(user)
                    }
            }
        }

    }

    override fun getFavoriteUsers(): List<UserRealm> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeFavorite(login: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveToRealm(user: GithubUser, localLogin: String) {

    }

    fun addReposToRealm(repos: List<UserRepo>) {

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