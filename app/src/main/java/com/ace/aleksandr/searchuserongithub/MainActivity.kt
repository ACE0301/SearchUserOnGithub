package com.ace.aleksandr.searchuserongithub

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.ace.aleksandr.searchuserongithub.data.ApiHolder
import com.ace.aleksandr.searchuserongithub.data.ApiHolder.service
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {


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
            getnfo()

        }
    }

    internal fun getnfo() {

        val call: Call<GithubUserInfo> = ApiHolder.service.getUserInfo(editText.text.toString())
        //val call2: Call<GithubUser> = ApiHolder.service.getUser(editText.text.toString())
        //val call3: Call<GetUserRepos> = ApiHolder.service.getUserRepos(editText.text.toString())


        call.enqueue(object : Callback<GithubUserInfo> {
            override fun onResponse(call: Call<GithubUserInfo>, response: Response<GithubUserInfo>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseQuantity = userInfo!!.total_count
                    userInfo.items.map {
                        listOf.add(it.login)
                    }

                    tvQuantity.text = "Найдено " + responseQuantity!! + " пользователей"
                    rvList.adapter = ListAdapter(this@MainActivity, listOf, this@MainActivity)
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

