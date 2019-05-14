package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

interface FavoriteUserInfoView {
    fun showUserInfo(login: String)
    fun showUserRepos(login: String)
    fun showError(errorText: String)
}