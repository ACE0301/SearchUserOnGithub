package com.ace.aleksandr.searchuserongithub.model

/**
 * https://api.github.com/search/users?q=%username%
 *
 * **/

data class GithubUserInfoSearchResult(

    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<SearchUserInfo>
)
