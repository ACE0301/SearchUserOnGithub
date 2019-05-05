package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.base.BasePresenter


class BookmarksPresenter(view: BookmarksView) : BasePresenter<BookmarksView>(view) {
    override fun onCreate() {
        getUserFromRealm()
    }

    fun getUserFromRealm() {
        view?.showUsersBookmarks()
    }

    override fun onDestroy() {
        //realm.close()
    }
}