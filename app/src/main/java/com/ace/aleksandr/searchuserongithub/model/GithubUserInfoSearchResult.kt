package com.ace.aleksandr.searchuserongithub.model


data class GithubUserInfoSearchResult(
    val total_count: Int,
    val items: List<SearchUserInfo>
)

data class SearchUserInfo(
    val login: String
)
