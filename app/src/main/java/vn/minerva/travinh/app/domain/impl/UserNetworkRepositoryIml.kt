package vn.minerva.travinh.app.domain.impl

import io.reactivex.Observable
import kotlinex.string.getValueOrDefaultIsEmpty
import okhttp3.Credentials
import vn.minerva.travinh.app.config.ConfigUtil
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.AppVersionResponse
import vn.minerva.travinh.app.data.network.response.PassportResponse
import vn.minerva.travinh.app.domain.UserNetworkRepository
import vn.minerva.travinh.app.network.ApiClient
import vn.minerva.travinh.app.network.TravinhService

class UserNetworkRepositoryIml : UserNetworkRepository {
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