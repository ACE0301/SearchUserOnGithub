package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.view.usersearch.UserSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookmarksPresenter(view: BookmarksView): BasePresenter<BookmarksView>(view) {
    override fun onCreate() {
    }

    private var disposableGetUserInfoBookmarks: Disposable? = null

    fun showUsersBookmarks() {

        if (disposableGetUserInfoBookmarks != null && disposableGetUserInfoBookmarks?.isDisposed == false) {
            disposableGetUserInfoBookmarks?.dispose()
        }
    }

    override fun onDestroy() {
        disposableGetUserInfoBookmarks?.dispose()
    }
}