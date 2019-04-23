package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import io.reactivex.Single

interface UserInfoCacheDataSource {
    fun list(): Single<List<GithubUser>>

    fun save(githubUser: List<GithubUser>)

    fun save(githubUser: GithubUser)

    fun delete(id: Long): Single<Any>
}