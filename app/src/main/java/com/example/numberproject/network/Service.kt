package com.example.numberproject.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://numbersapi.com"

interface NumberApiService {
    @GET("{number}")
    suspend fun getFact(@Path("number") number: Double): Call<String>

    @GET("random/math")
    suspend fun getRandomFact() : Call<String>
}
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object NumberApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: NumberApiService by lazy { retrofit.create(NumberApiService::class.java) }
}