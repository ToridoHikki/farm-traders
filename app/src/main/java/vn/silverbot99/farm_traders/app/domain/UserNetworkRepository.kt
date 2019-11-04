package vn.silverbot99.farm_traders.app.domain

import io.reactivex.Observable
import vn.silverbot99.farm_traders.app.data.network.request.*
import vn.silverbot99.farm_traders.app.data.network.response.*

interface UserNetworkRepository {
    fun checkVersion(): Observable<AppVersionResponse>
    fun reLogin(): Observable<PassportResponse>
    fun login(loginRequest: PassportRequest): Observable<PassportResponse>
    fun getMedicalDetail(requestBody: MedicalRequest): Observable<MedicalResponse>
    fun getGasolineStoreDetail(requestBody: GasolineStoreRequest): Observable<GasolineStoreResponse>
    fun getGasolineStore(requestBody: GasolineStoreDetailRequest): Observable<GasolineStoreDetailResponse>
    fun getMedicalInfo(requestBody: MedicalDetailRequest): Observable<MedicalDetailResponse>
    fun getNews(requestBody: NewsRequest): Observable<NewsResponse>
}