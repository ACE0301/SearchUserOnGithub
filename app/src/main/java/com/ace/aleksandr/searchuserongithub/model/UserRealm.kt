package com.ace.aleksandr.searchuserongithub.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class UserRealm(
    var isFavorite: Boolean = false,
    @PrimaryKey
    var login: String? = null,
    var name: String? = null,
    var location: String? = null,
    var listOfRepos: RealmList<UserRepository>? = null
) : RealmObject()

open class UserRepository(
    var name: String? = null
) : RealmObject()



