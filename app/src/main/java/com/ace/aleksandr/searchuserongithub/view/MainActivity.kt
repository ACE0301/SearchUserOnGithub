package com.ace.aleksandr.searchuserongithub.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.view.usersearch.UserSearchFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSearchFragment()
    }

    private fun showSearchFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.content,
                UserSearchFragment.newInstance(),
                UserSearchFragment.TAG
            )
            .commit()
    }
}

