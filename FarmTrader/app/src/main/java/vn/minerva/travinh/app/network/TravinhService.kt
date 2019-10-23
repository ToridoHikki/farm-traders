package vn.minerva.travinh.app.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import vn.minerva.travinh.app.data.network.response.AppVersionResponse
import vn.minerva.travinh.app.data.network.response.PassportResponse

interface TravinhService {
    companion object {
        const val VERSION = "v1"
    }

    @GET("$VERSION/user/login")
    fun login(@Header("Authorization") auth: String): Observable<PassportResponse>

    @POST("$VERSION/static/version_android")
    fun getAppVersion(): Observable<AppVersionResponse>
}