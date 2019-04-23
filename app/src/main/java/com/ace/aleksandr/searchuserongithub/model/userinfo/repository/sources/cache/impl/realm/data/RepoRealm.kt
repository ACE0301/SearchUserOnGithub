package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RepoRealm(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null,
    var location: String? = null
    //var listOfRepos: RealmList<String>
) : RealmObject(){

    companion object {
        val FIELD_ID = "id"
    }
}



