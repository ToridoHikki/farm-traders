package vn.silverbot99.farm_traders.app.domain.impl

import io.reactivex.Observable
import kotlinex.string.getValueOrDefaultIsEmpty
import okhttp3.Credentials
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.app.data.network.request.*
import vn.silverbot99.farm_traders.app.data.network.response.*
import vn.silverbot99.farm_traders.app.domain.UserNetworkRepository
import vn.silverbot99.farm_traders.app.network.ApiClient
import vn.silverbot99.farm_traders.app.network.TravinhService

class UserNetworkRepositoryIml : UserNetworkRepository {
    override fun getCatalogies(): Observable<CategoriesResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getCatalogies()
    }

    override fun getNews(requestBody: NewsRequest): Observable<NewsResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getNews(requestBody)
    }

    override fun getMedicalInfo(requestBody: MedicalDetailRequest): Observable<MedicalDetailResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getMedicalInfo(requestBody.id)
    }

    override fun getGasolineStore(requestBody: GasolineStoreDetailRequest): Observable<GasolineStoreDetailResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getGasolineStore(requestBody.id)
    }

    override fun getGasolineStoreDetail(requestBody: GasolineStoreRequest): Observable<GasolineStoreResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getGasolineStoreDetail(requestBody)    }

    override fun getMedicalDetail(requestBody: MedicalRequest): Observable<MedicalResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getMedicalDetail(requestBody)
    }

    override fun checkVersion(): Observable<AppVersionResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        return coopService.getAppVersion()
    }

    override fun reLogin(): Observable<PassportResponse> {
        ConfigUtil.saveIsFirstLoginApp(false)
        val coopService: TravinhService = ApiClient.getClient()
        val loginRequest = ConfigUtil.passportRequest
        val authInBase64 = Credentials.basic(loginRequest?.user.getValueOrDefaultIsEmpty(), loginRequest?.pass.getValueOrDefaultIsEmpty())
        return coopService.login(authInBase64)
    }

    override fun login(loginRequest: PassportRequest): Observable<PassportResponse> {
        val coopService: TravinhService = ApiClient.getClient()
        val authInBase64 = Credentials.basic(loginRequest.user, loginRequest.pass)
        ConfigUtil.saveLoginRequest(loginRequest)
        return coopService.login(authInBase64)
    }
}