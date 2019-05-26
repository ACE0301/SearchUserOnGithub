package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.model.UserRealm

interface FavoriteUsersView {
    fun showFavoriteUsers(users: List<UserRealm>)
    fun showError(errorText: String)
}