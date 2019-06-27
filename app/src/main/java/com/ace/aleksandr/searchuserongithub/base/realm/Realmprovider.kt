package com.ace.aleksandr.searchuserongithub.base.realm

import io.realm.Realm

interface RealmProvider {
    val realm: Realm
}

fun createRealmProvider(): RealmProvider =
    DefaultRealmProvider

object DefaultRealmProvider : RealmProvider {
    override val realm: Realm
        get() = Realm.getDefaultInstance()
}