package com.ace.aleksandr.searchuserongithub.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo
import kotlinx.android.synthetic.main.activity_repos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposActivity : AppCompatActivity(),
    UserAndReposAdapter.OnItemClickListener {


    lateinit var login: String
    lateinit var textView: TextView
    var responseName: String? = ""
    var responseLocation: String? = ""
    lateinit var tvAboutUser: TextView
    var listOfRepos = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        textView = findViewById(R.id.tvLogin)
        tvAboutUser = findViewById(R.id.tvAboutUser)
        rvList2.layoutManager = LinearLayoutManager(this)


        val intent = intent

        login = intent.getStringExtra("login")
        textView.text = "My login is : $login"
        getnfo()
    }

    internal fun getnfo() {

        val call2: Call<GithubUser> = ApiHolder.service.getUser(login)
        val call3: Call<List<UserRepo>> = ApiHolder.service.getUserRepos(login)
        call2.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.code() == 200) {
                    val userInfo = response.body()

                    responseName = userInfo?.name
                    responseLocation = userInfo?.location
                    tvAboutUser.text = "Имя пользователя " + responseName + " из города " + responseLocation

                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
            }

        })
        call3.enqueue(object : Callback<List<UserRepo>> {
            override fun onResponse(call: Call<List<UserRepo>>, response: Response<List<UserRepo>>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    userInfo?.map {
                        listOfRepos.add(it.name ?: "пустое имя")
                    }
                }
                rvList2.adapter = UserAndReposAdapter(
                    this@ReposActivity,
                    listOfRepos,
                    this@ReposActivity
                )
                //tvListOfRepos.text = "Имя репозитория " + responseListOfRepos!!
            }


            override fun onFailure(call: Call<List<UserRepo>>, t: Throwable) {
            }

        })
    }

    override fun onItemClick(view: View, position: Int) {
    }
}