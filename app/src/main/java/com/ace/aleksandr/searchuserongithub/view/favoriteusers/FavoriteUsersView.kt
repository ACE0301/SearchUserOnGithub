package com.ace.aleksandr.searchuserongithub.view.favoriteusers

import com.ace.aleksandr.searchuserongithub.model.UserRealm
import io.realm.RealmResults

interface FavoriteUsersView {
    fun showFavoriteUsers(users: RealmResults<UserRealm>)
    fun showError(errorText: String)
}