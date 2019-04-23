package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.RepoRealm

class UserInfoToRealmObjectMapper : Mapper<GithubUser, RepoRealm> {
    override fun map(from: GithubUser): RepoRealm =
        RepoRealm(
            id = from.id,
            name = from.name,
            location = from.location
        )
}