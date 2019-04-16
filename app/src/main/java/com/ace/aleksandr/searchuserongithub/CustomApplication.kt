package com.ace.aleksandr.searchuserongithub

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        try {
            Realm.setDefaultConfiguration(configuration)
            Realm.getDefaultInstance()

        } catch (e: Exception) {
            Realm.deleteRealm(configuration)
        }
    }
}
