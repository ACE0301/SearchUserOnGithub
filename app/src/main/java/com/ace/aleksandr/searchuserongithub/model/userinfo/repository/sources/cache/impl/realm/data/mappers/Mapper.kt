package com.ace.aleksandr.searchuserongithub.model.userinfo.repository.sources.cache.impl.realm.data.mappers

interface Mapper<F, T> {

    fun map(from: F): T

}