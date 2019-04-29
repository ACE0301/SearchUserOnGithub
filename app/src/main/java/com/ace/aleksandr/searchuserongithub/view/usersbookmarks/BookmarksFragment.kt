package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_bookmarks.*

class BookmarksFragment : Fragment(), BookmarksView {


    companion object {
        const val TAG = "BookmarksFragment"

        fun newInstance(tag: String) = BookmarksFragment()

    }

    private val presenter by lazy { BookmarksPresenter(this) }
    private val mAdapter = BookmarksAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_bookmarks, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }


    override fun showUsersBookmarks(users: RepoRealm) {
//        val realm = Realm.getDefaultInstance()
//        val userName = {
//            realm.where(RepoRealm::class.java)
//                .findAll()

        tvSavedUsers.text = users.toString()
    }

    override fun showError(errorText: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}