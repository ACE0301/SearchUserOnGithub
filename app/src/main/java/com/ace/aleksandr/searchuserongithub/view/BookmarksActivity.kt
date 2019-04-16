package com.ace.aleksandr.searchuserongithub.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ace.aleksandr.searchuserongithub.view.userrepoinfo.UserReposAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_bookmarks.*


class BookmarksActivity : AppCompatActivity() {
    var name: String? = ""
    var location: String? = ""
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ace.aleksandr.searchuserongithub.R.layout.activity_bookmarks)
        rvListOfUsersInBookmarks.layoutManager = LinearLayoutManager(this)
        val intent = intent
        name = intent.getStringExtra("name")
        location = intent.getStringExtra("location")

        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
//        var objectToRealm = RepoRealm(
//            name ?: "",
//            location ?: ""
//        )
//        val manageRepos = realm.copyToRealm(objectToRealm)
        realm.commitTransaction()
        rvListOfUsersInBookmarks.adapter = SavedUsersAdapter(

        )


    }

    public override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}

