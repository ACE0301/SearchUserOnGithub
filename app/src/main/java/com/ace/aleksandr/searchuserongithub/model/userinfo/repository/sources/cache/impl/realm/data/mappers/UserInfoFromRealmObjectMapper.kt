package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers

import com.ace.aleksandr.searchuserongithub.model.userinfo.data.GithubUser
import com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.RepoRealm

class UserInfoFromRealmObjectMapper : Mapper<RepoRealm, GithubUser> {
    override fun map(from: RepoRealm): GithubUser = GithubUser(
        id = from.id,
        name = from.name,
        location = from.location
    )

}