package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.UserInfoCacheDataSource
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers.Mapper
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers.UserInfoFromRealmObjectMapper
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers.UserInfoToRealmObjectMapper
import io.reactivex.Single
import io.realm.Realm

class UserInfoRealmCacheDataSource(
    private val userInfoFromRealmObjectMapper: Mapper<RepoRealm, GithubUser> = UserInfoFromRealmObjectMapper(),
    private val userInfoToRealmObjectMapper: Mapper<GithubUser, RepoRealm> = UserInfoToRealmObjectMapper()

) : UserInfoCacheDataSource {
    override fun list(): Single<List<GithubUser>> {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(githubUser: List<GithubUser>) {
        Realm.getDefaultInstance().use { realm ->
            val users = githubUser.map(userInfoToRealmObjectMapper::map)
            realm.executeTransaction { transactionRealm ->
                transactionRealm.delete(RepoRealm::class.java)
                transactionRealm.copyToRealm(users)

            }


        }
    }

    override fun save(githubUser: GithubUser) {
        Realm.getDefaultInstance().use { realm ->
            val user = userInfoToRealmObjectMapper.map(githubUser)
            realm.executeTransaction { transactionRealm ->
                transactionRealm.copyToRealmOrUpdate(user)
            }
        }
    }

    override fun delete(id: Long): Single<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}