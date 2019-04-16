package com.ace.aleksandr.searchuserongithub.data.api

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfo
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiInterface {

    @GET("/search/users")
    fun getUserInfo(@Query("q") username: String): Single<GithubUserInfo>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUser>

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<List<UserRepo>>


}