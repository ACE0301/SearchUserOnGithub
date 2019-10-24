package com.ace.aleksandr.searchuserongithub.model

/**
 *
 * https://api.github.com/users/%username%
 *
 * **/

data class GithubUser(
    val name: String?,
    val location: String?
)

