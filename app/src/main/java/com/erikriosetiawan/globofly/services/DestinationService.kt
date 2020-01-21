package com.erikriosetiawan.globofly.services

import com.erikriosetiawan.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET

interface DestinationService {

    @GET("destination")
    fun getDestinationList(): Call<List<Destination>>
}