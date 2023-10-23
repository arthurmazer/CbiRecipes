package com.mazer.cbirecipes.data.local

interface CacheDataSource {
    fun saveCacheData(key: String, value: String)
    fun loadCacheData(key: String, defaultValue: String): String
}