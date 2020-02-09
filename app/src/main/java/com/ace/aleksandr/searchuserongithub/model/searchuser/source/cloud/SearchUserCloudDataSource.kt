package com.ace.aleksandr.searchuserongithub.model.searchuser.source.cloud

import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfoSearchResult
import io.reactivex.Single

interface SearchUserCloudDataSource {
    fun getUserInfo(username: String): Single<GithubUserInfoSearchResult>
}

class SearchUserCloudDataSourceImpl : SearchUserCloudDataSource {
    override fun getUserInfo(username: String): Single<GithubUserInfoSearchResult> {
        return ApiHolder.service.getUserInfo(username)
    }
}