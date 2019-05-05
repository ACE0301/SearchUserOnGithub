package com.ace.aleksandr.searchuserongithub.view.usersbookmarks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_bookmarks.*

class BookmarksFragment : Fragment(), BookmarksView {
    private var mRealm: Realm? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListOfUsersInBookmarks.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }
    override fun showUsersBookmarks() {

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { inRealm ->
                val users = inRealm.where(RepoRealm::class.java!!).findAll()
                mAdapter.data = users.map { it.toString() }
            }
        }
    }

    override fun showError(errorText: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm?.close()
    }
}