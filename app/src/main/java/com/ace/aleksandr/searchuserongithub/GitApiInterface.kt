package com.ace.aleksandr.searchuserongithub

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiInterface {

    @GET("/search/users")
    fun getUserInfo(@Query("q") username: String): Call<GithubUserInfo>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUser>

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Call<GetUserRepos>


}