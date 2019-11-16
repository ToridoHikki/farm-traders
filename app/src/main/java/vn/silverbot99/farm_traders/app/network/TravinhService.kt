package vn.silverbot99.farm_traders.app.network

import io.reactivex.Observable
import retrofit2.http.*
import vn.silverbot99.farm_traders.app.data.network.request.GasolineStoreRequest
import vn.silverbot99.farm_traders.app.data.network.request.MedicalRequest
import vn.silverbot99.farm_traders.app.data.network.request.NewsRequest
import vn.silverbot99.farm_traders.app.data.network.response.*

interface TravinhService {
    companion object {
        const val VERSION = "v1"
    }

    @GET("getCategories")
    fun getCatalogies(): Observable<CategoriesResponse>


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