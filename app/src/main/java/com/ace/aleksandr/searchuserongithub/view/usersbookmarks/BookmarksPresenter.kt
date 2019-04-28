package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.db.UserDbSource
import io.realm.Realm

class BookmarksPresenter(view: BookmarksView) : BasePresenter<BookmarksView>(view) {
    override fun onCreate() {
    }


    fun showUsersBookmarks() {
        val realm = Realm.getDefaultInstance()
        realm.where()

    }

    override fun onDestroy() {
    }
}