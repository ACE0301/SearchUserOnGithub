package com.ace.aleksandr.searchuserongithub.model.searchuser.repository

import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.searchuser.data.UsersPresModel
import io.reactivex.Single

interface SearchUserRepository {
    fun getUserInfo(name: String): Single<UsersPresModel>
}

class SearchUserRepositoryImpl(
    private val usersFromApiToModelMapper: UsersFromApiToModelMapper = UsersFromApiToModelMapperImpl()
) :
    SearchUserRepository {
    override fun getUserInfo(name: String): Single<UsersPresModel> {
        return ApiHolder.service.getUserInfo(name).map {
            usersFromApiToModelMapper.map(it)
        }
    }
}