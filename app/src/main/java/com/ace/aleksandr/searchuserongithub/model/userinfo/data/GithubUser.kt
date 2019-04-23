package com.ace.aleksandr.searchuserongithub.model.userinfo.data

/**
 * https://api.github.com/users/%username%
 * используются поля:
 * name
 * location
 * **/


data class GithubUser(
    //val login: String? = null,
    var id: Int? = null,
    /*val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val site_admin: Boolean? = null,*/
    var name: String? = null,
    /*val company: Any? = null,
    val blog: String? = null,*/
    var location: String? = null
    /*val email: Any? = null,
    val hireable: Boolean? = null,
    val bio: Any? = null,
    val public_repos: Int? = null,
    val public_gists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null*/
)