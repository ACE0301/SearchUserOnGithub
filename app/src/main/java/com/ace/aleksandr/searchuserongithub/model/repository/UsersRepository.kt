package com.ace.aleksandr.searchuserongithub.model.repository

import com.ace.aleksandr.searchuserongithub.model.RepoRealm
import io.reactivex.Completable
import io.reactivex.Observable

interface UsersRepository {
    fun search(query: String): Observable<List<RepoRealm>>

    fun remove(userId: Long): Completable

}