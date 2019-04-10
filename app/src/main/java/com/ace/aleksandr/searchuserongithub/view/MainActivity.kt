package com.ace.aleksandr.searchuserongithub.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.GithubUserInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),
    UserAndReposAdapter.OnItemClickListener {


    var responseQuantity: Int? = null
    var listOf = mutableListOf<String>()
    lateinit var editText: EditText
    lateinit var tvQuantity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvQuantity = findViewById(R.id.tvQuantity)
        editText = findViewById(R.id.editText)
        rvList.layoutManager = LinearLayoutManager(this)
        button.setOnClickListener {
            getInfo()
        }
    }

    private fun getInfo() {

        val call: Call<GithubUserInfo> = ApiHolder.service.getUserInfo(editText.text.toString())

        call.enqueue(object : Callback<GithubUserInfo> {
            override fun onResponse(call: Call<GithubUserInfo>, response: Response<GithubUserInfo>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseQuantity = userInfo!!.total_count
                    listOf.clear()
                    userInfo.items.map {
                        listOf.add(it.login)
                    }

                    tvQuantity.text = "Найдено " + responseQuantity!! + " пользователей"
                    rvList.adapter = UserAndReposAdapter(
                        this@MainActivity,
                        listOf,
                        this@MainActivity
                    )
                }
            }

            override fun onFailure(call: Call<GithubUserInfo>, t: Throwable) {
            }
        })
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, ReposActivity::class.java)
        intent.putExtra("login", listOf[position])
        startActivity(intent)
    }
}

