package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.model.searchuser.data.UsersPresModel

interface UserSearchView {
    fun showUserInfo(userSearchResult: UsersPresModel)
    fun showError(errorText: String)
    fun isShowLoading(status: Boolean)
}