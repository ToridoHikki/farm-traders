package vn.minerva.travinh.app.network

import io.reactivex.Observable
import retrofit2.http.*
import vn.minerva.travinh.app.data.network.request.GasolineStoreRequest
import vn.minerva.travinh.app.data.network.request.MedicalRequest
import vn.minerva.travinh.app.data.network.request.NewsRequest
import vn.minerva.travinh.app.data.network.response.*

interface TravinhService {
    companion object {
        const val VERSION = "v1"
    }

    @GET("$VERSION/user/login")
    fun login(@Header("Authorization") auth: String): Observable<PassportResponse>

    @POST("$VERSION/static/version_android")
    fun getAppVersion(): Observable<AppVersionResponse>

    @POST("$VERSION/user/query/medical")
    fun getMedicalDetail(@Body requestBody: MedicalRequest): Observable<MedicalResponse>

    @POST("$VERSION/user/query/gasoline_store")
    fun getGasolineStoreDetail(@Body requestBody: GasolineStoreRequest): Observable<GasolineStoreResponse>

    @GET("$VERSION/user/query/gasoline_store/{id}")
    fun getGasolineStore(@Path("id")id:Int): Observable<GasolineStoreDetailResponse>

    @GET("$VERSION/user/query/medical/{id}")
    fun getMedicalInfo(@Path("id")id:Int): Observable<MedicalDetailResponse>

    @POST("$VERSION/user/query/news")
    fun getNews(@Body requestBody: NewsRequest): Observable<NewsResponse>


}