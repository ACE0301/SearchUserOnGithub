package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.realm.Realm


class BookmarksPresenter(view: BookmarksView) : BasePresenter<BookmarksView>(view) {
    override fun onCreate() {

    }


    fun getUserFromRealm() {
        val realm = Realm.getDefaultInstance()
        val users = realm.where(RepoRealm::class.java).findAll()
    }

    override fun onDestroy() {
        //realm.close()
    }
}