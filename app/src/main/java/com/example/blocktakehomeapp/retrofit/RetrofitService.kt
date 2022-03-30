package com.example.blocktakehomeapp.retrofit

import com.example.blocktakehomeapp.employees.Employee
import com.example.blocktakehomeapp.employees.EmployeesList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("sq-mobile-interview/employees.json")
    suspend fun getAllEmployees() : Response<EmployeesList>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder().baseUrl("https://s3.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }

            return retrofitService!!
        }
    }
}