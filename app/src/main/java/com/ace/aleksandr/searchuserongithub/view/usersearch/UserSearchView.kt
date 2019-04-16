package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.model.GithubUserInfo

interface UserSearchView {
    fun showUserInfo(user: GithubUserInfo)
    fun showError(errorText: String)
    fun isShowLoading(status: Boolean)
}