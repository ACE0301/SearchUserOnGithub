package com.ace.aleksandr.searchuserongithub.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RepoRealm(
    @PrimaryKey var id: Long = -1,
    var nameofUser: String? = null,
    var location: String? = null,
    var listOfRepos: RealmList<String>? = null
) : RealmObject()



