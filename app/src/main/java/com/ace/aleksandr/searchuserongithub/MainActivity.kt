package com.ace.aleksandr.searchuserongithub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.github.com"
    var responseQuantity: Int? = null
    var responseName: String? = null
    var responseLocation: String? = null
    var responseListOfRepos: String? = null
    lateinit var editText: EditText
    lateinit var textV: TextView
    lateinit var tvAboutUser: TextView
    lateinit var tvListOfRepos: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textV = textView
        tvAboutUser = findViewById(R.id.tvAboutUser)
        editText = findViewById(R.id.editText)
        tvListOfRepos = findViewById(R.id.tvListOfRepos)

        button.setOnClickListener {
            getnfo()
        }
    }

    internal fun getnfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitApiInterface::class.java)
        val call: Call<GithubUserInfo> = service.getUserInfo(editText.text.toString())
        val call2: Call<GithubUser> = service.getUser(editText.text.toString())
        val call3: Call<GetUserRepos> = service.getUserRepos(editText.text.toString())



        call.enqueue(object : Callback<GithubUserInfo> {
            override fun onResponse(call: Call<GithubUserInfo>, response: Response<GithubUserInfo>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseQuantity = userInfo!!.total_count
                    textV.text = "Найдено " + responseQuantity!! + " пользователей"

                }
            }

            override fun onFailure(call: Call<GithubUserInfo>, t: Throwable) {
            }
        })
        call2.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseName = userInfo!!.name
                    responseLocation = userInfo!!.location
                    tvAboutUser.text = "Имя пользователя " + responseName!! + " из города " + responseLocation
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
            }

        })
        call3.enqueue(object : Callback<GetUserRepos> {
            override fun onResponse(call: Call<GetUserRepos>, response: Response<GetUserRepos>) {
                if (response.code() == 200) {
                    val userInfo = response.body()
                    responseListOfRepos = userInfo!!.name
                    tvListOfRepos.text = "Имя репозитория " + responseListOfRepos!!
                }
            }

            override fun onFailure(call: Call<GetUserRepos>, t: Throwable) {
            }

        })
    }
}

