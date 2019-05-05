package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import com.ace.aleksandr.searchuserongithub.model.UserRepo

interface BookmarksView {
    fun showUsersBookmarks()
    //fun showUserRepos(userRepos: List<UserRepo>)
    fun showError(errorText: String)
}