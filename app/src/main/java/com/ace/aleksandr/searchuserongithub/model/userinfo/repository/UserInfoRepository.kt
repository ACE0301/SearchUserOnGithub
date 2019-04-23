package com.ace.aleksandr.searchuserongithub.model.userinfo.repository

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import io.reactivex.Single

interface UserInfoRepository {

        fun save(githubUser: GithubUser): Single<GithubUser>

        fun list(fromCache: Boolean = false): Single<List<GithubUser>>

}