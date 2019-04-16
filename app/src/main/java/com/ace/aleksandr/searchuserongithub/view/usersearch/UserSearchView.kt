package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.model.GithubUserInfoSearchResult

interface UserSearchView {
    fun showUserInfo(userSearchResult: GithubUserInfoSearchResult)
    fun showError(errorText: String)
    fun isShowLoading(status: Boolean)
}