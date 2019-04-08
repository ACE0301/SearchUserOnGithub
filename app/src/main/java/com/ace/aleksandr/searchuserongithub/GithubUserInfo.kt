package com.ace.aleksandr.searchuserongithub

data class GithubUserInfo(

    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)
