package com.ace.aleksandr.searchuserongithub.view

interface UserView {
    fun openFavoritesFragment()
    fun openFavoriteUserInfoFragment(login: String)
    fun openNewFragment(login: String)
}