package com.be2.test3.network

import com.google.gson.GsonBuilder
import com.be2.test3.models.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST


interface DataApi {

    @POST("test.php")
    suspend fun getMovies() : Response<List<Movie>>




    companion object{
        operator fun invoke() : DataApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://www.rigidohub.xyz/browser/api/")
                .build()
                .create(DataApi::class.java)


        }
    }
}
