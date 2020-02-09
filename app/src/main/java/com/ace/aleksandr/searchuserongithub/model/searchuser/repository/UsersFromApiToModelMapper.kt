package com.ace.aleksandr.searchuserongithub.model.searchuser.repository

import com.ace.aleksandr.searchuserongithub.model.GithubUserInfoSearchResult
import com.ace.aleksandr.searchuserongithub.model.searchuser.data.UsersPresModel

interface UsersFromApiToModelMapper {
    fun map(users: GithubUserInfoSearchResult): UsersPresModel
}

class UsersFromApiToModelMapperImpl : UsersFromApiToModelMapper {
    override fun map(users: GithubUserInfoSearchResult): UsersPresModel {
        return UsersPresModel(
            total_count = "Найдено ${users.total_count} пользователей",
            logins = users.items.map {
                it.login
            }
        )
    }
}