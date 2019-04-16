package com.ace.aleksandr.searchuserongithub.model.repository

import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepoRepository {

    fun get(login: String) {
        val call2: Call<GithubUser> = ApiHolder.service.getUser(login)
        call2.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
            }

        })
    }
}