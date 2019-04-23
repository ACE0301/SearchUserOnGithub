package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo

interface BookmarksView {
    fun showUser(user: GithubUser)
    fun showUserRepos(userRepos: List<UserRepo>)
    fun showError(errorText: String)

}