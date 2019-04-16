package com.ace.aleksandr.searchuserongithub.view.userrepoinfo

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo

interface UserReposView {
    fun showUser(user: GithubUser)
    fun showUserRepos(userRepos: List<UserRepo>)
    fun showError(errorText: String)
}