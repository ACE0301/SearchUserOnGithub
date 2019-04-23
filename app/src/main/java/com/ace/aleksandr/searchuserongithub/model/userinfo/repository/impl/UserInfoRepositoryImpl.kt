package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.impl

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.UserInfoRepository
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.UserInfoCacheDataSource
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.UserInfoRealmCacheDataSource
import io.reactivex.Single

class UserInfoRepositoryImpl(
    private val userInfoCacheDataSource: UserInfoCacheDataSource = UserInfoRealmCacheDataSource()

) : UserInfoRepository {
    override fun save(githubUser: GithubUser): Single<GithubUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(fromCache: Boolean): Single<List<GithubUser>> =
        userInfoCacheDataSource.list()
}


