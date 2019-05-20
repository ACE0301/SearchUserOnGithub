package com.ace.aleksandr.searchuserongithub.model

/**
 *
 * https://api.github.com/users/%username%
 *
 * **/


data class GithubUser(
    var name: String? = null,
    var location: String? = null
)

