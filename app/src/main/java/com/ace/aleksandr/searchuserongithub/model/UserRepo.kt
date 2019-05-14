package com.ace.aleksandr.searchuserongithub.model

/**
 *
 * поиск имен репозиториев по юзеру
 * https://api.github.com/users/%username%/repos
 *
 * **/
data class UserRepo(
    val name: String?
)
