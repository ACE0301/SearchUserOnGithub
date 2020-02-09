package com.ace.aleksandr.searchuserongithub.model.repository

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.reactivex.Completable
import io.reactivex.Single

interface UsersRepository {
    fun getUser(login: String): Single<UserRealm>

    fun saveUser(user: GithubUser, localLogin: String)

    fun saveRepositories(login: String?, repositories: List<UserRepo>)

    fun getFavoriteUsers(): Single<List<UserRealm>>

    fun deleteFavoriteUser(login: String): Single<List<UserRealm>>

    fun makeFavorite(login: String): Completable
}