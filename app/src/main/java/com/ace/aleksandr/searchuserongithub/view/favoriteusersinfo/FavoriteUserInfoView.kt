package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import com.ace.aleksandr.searchuserongithub.model.UserRealm

interface FavoriteUserInfoView {
    fun showUserInfo(login: UserRealm)
    //fun showUserRepos(login: String)
    fun showError(errorText: String)
}