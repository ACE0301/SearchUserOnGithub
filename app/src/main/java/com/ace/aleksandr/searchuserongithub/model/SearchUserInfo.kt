package com.ace.aleksandr.searchuserongithub.model

/**
 *
 * Поиск пользователей по логину
 * https://api.github.com/search/users?q=%username%
 *
 * **/

data class SearchUserInfo(
    val login: String
)