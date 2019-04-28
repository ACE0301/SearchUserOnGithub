package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import android.support.v4.app.Fragment
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_bookmarks.*

class BookmarksFragment : Fragment(), BookmarksView {


    companion object {
        const val TAG = "BookmarksFragment"

        fun newInstance(tag: String) = BookmarksFragment()

    }

    private val presenter by lazy { BookmarksPresenter(this) }
    private val mAdapter = BookmarksAdapter()

    override fun showUsersBookmarks(users: RepoRealm) {
        val realm = Realm.getDefaultInstance()
        val userName = {
            realm.where(RepoRealm::class.java)
                .findAll()
        }

        tvSavedUsers.text = userName.toString()
    }

    override fun showError(errorText: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}