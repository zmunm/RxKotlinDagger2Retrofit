package com.zmunm.narvcorp.sample.api

import com.zmunm.narvcorp.sample.dao.Admin
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AdminApi{
    @GET("/admins")
    fun select(
            @Query(ID)id:String,
            @Query(PASSWORD)password:String
    ):Observable<Admin?>
}