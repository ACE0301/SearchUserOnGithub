package com.ace.aleksandr.searchuserongithub.data.api

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfoSearchResult
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiInterface {

    @GET("/search/users")
    fun getUserInfo(@Query("q") username: String): Single<GithubUserInfoSearchResult>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<GithubUser>

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Single<List<UserRepo>>

}