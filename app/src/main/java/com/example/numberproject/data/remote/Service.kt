package com.example.numberproject.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.math.BigInteger

private const val BASE_URL = "http://numbersapi.com"

interface NumberApiService {
    @GET("{number}")
    suspend fun getFact(@Path("number") number: Long): Response<String> // Suspend function with Response

    @GET("random/math")
    suspend fun getRandomFact(): Response<String> // Suspend function with Response
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