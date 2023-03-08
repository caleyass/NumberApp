package com.example.numberproject.data.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.math.BigInteger

private const val BASE_URL = "http://numbersapi.com"

interface NumberApiService {
    @GET("{number}")
    fun getFact(@Path("number") number: Long): Call<String>

    @GET("random/math")
    fun getRandomFact() : Call<String>
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