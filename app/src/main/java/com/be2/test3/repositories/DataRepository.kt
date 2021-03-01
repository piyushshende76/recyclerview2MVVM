package com.be2.test3.repositories

import com.be2.test3.network.DataApi


class DataRepository(
    private val api: DataApi
) : SafeApiRequest() {

    suspend fun getMovies() = apiRequest { api.getMovies() }

}