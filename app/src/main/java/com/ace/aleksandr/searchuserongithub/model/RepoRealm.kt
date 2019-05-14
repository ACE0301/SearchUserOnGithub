package com.ace.aleksandr.searchuserongithub.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class RepoRealm(
    @PrimaryKey
    var id: Int? = 0,
    var isFavorite: Boolean = false,
    var login: String? = null,
    var name: String? = null,
    var location: String? = null,
    var listOfRepos: RealmList<Repos>? = null
) : RealmObject()

open class Repos(
    var name: String? = null
) : RealmObject()



