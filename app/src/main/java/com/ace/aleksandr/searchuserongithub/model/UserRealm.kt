package com.ace.aleksandr.searchuserongithub.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class UserRealm(
    @PrimaryKey
    var login: String? = null,
    var isFavorite: Boolean? = null,
    var name: String? = null,
    var location: String? = null,
    var listOfRepos: RealmList<UserRepository>? = null
) : RealmObject()

open class UserRepository(
    var name: String? = null
) : RealmObject()



