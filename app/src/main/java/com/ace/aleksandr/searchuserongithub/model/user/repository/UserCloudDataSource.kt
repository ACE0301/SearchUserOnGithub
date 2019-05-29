package com.ace.aleksandr.searchuserongithub.model.user.repository

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.SearchUserInfo
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.reactivex.Single

interface UserCloudDataSource {

    fun getUsers(name: String): Single<SearchUserInfo>

    fun getUserInfo(login: String): Single<GithubUser>

    fun getRepositories(login: String): Single<List<UserRepo>>

}