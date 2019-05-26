package com.ace.aleksandr.searchuserongithub.view.userinfo

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo


interface UserInfoView {
    fun showUser(user: GithubUser)
    fun showUserRepos(userRepos: List<UserRepo>)
    fun showResult(resultText: String)
}
