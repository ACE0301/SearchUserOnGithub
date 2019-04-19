package com.ace.aleksandr.searchuserongithub.db.migration.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RepoRealm(
    @PrimaryKey
    var id: Long,
    var nameofUser: String,
    var location: String,
    var listOfRepos: RealmList<String>
) : RealmObject()



