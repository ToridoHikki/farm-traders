package vn.minerva.travinh.app.domain

import io.reactivex.Observable
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.AppVersionResponse
import vn.minerva.travinh.app.data.network.response.PassportResponse

interface UserNetworkRepository {
    fun checkVersion(): Observable<AppVersionResponse>
    fun reLogin(): Observable<PassportResponse>
    fun login(loginRequest: PassportRequest): Observable<PassportResponse>
}