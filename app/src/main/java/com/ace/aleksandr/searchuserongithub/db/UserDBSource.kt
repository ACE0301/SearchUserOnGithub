package com.ace.aleksandr.searchuserongithub.db

import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
/**
 *
 * класс пока не используется,
 * необходимо добавить методы для realm
 *
 * **/
class UserDbSource {
    private var mRealmConfiguration: RealmConfiguration? = null
    val users: Single<List<RepoRealm>>
        get() {
            val realm = Realm.getInstance(mRealmConfiguration)
            val users = realm.copyFromRealm(realm.where(RepoRealm::class.java).findAll())
            realm.close()
            return Single.just(users)
        }

    init {
        //mRealmConfiguration = CustomApplication.getUserConfiguration()
    }

    fun getUser(id: Long): Single<RepoRealm> {
        val realm = Realm.getInstance(mRealmConfiguration)
        var user: RepoRealm? = null
        val realmUser = realm.where(RepoRealm::class.java).equalTo("id", id).findFirst()
        if (realmUser != null) {
            user = realm.copyFromRealm(realmUser)
        }
        realm.close()
        return Single.just(user)
    }

    fun updateUsers(users: List<RepoRealm>): Completable {
        return Completable.fromAction {
            val realm = Realm.getInstance(mRealmConfiguration)
            realm.insertOrUpdate(users)
            realm.close()
            Realm.compactRealm(mRealmConfiguration)
        }
    }

    fun updateUser(user: RepoRealm): Completable {
        return Completable.fromAction {
            val realm = Realm.getInstance(mRealmConfiguration)
            realm.insertOrUpdate(user)
            realm.close()
            Realm.compactRealm(mRealmConfiguration)
        }
    }

}