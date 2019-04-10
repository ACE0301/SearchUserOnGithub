package com.ace.aleksandr.searchuserongithub.model

data class GithubUserInfo(

    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)
