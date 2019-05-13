package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.model.RepoRealm

interface FavoriteUsersInfoView {
    fun showUserInfo(login1: String)
    fun showUserRepos(userRepos: RepoRealm)
    fun showError(errorText: String)
}