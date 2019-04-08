package com.ace.aleksandr.searchuserongithub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.ace.aleksandr.searchuserongithub.data.ApiHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposActivity : AppCompatActivity() {


    lateinit var login: String
    lateinit var textView: TextView
    lateinit var responseName: String
    lateinit var responseLocation: String
    lateinit var tvAboutUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        textView = findViewById(R.id.tvLogin)
        tvAboutUser = findViewById(R.id.tvAboutUser)

        val intent = intent

        login = intent.getStringExtra("login")
        textView.text = "My login is : $login"
        getnfo()
    }

    internal fun getnfo() {

        val call2: Call<GithubUser> = ApiHolder.service.getUser(login)
        //val call3: Call<GetUserRepos> = ApiHolder.service.getUserRepos(login)
        call2.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseName = userInfo!!.name
                    responseLocation = userInfo!!.location
                    tvAboutUser.text = "Имя пользователя " + responseName!! + " из города " + responseLocation!!
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
            }

        })
//        call3.enqueue(object : Callback<GetUserRepos> {
//            override fun onResponse(call: Call<GetUserRepos>, response: Response<GetUserRepos>) {
//                if (response.code() == 200) {
//                    val userInfo = response.body()
//                    responseListOfRepos = userInfo!!.name
//                    tvListOfRepos.text = "Имя репозитория " + responseListOfRepos!!
//                }
//            }
//
//            override fun onFailure(call: Call<GetUserRepos>, t: Throwable) {
//            }
//
//        })
    }
}