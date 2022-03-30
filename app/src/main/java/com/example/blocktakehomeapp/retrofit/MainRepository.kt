package com.example.blocktakehomeapp.retrofit

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllEmployees() = retrofitService.getAllEmployees()
}