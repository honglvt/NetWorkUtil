package com.medtap.network.library.api

import com.medtap.network.library.basemodel.BaseModel
import com.medtap.network.library.data.HCDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface MainService {

    @GET("/test/mock")
    fun test(): Observable<BaseModel<HCDTO>>

}
