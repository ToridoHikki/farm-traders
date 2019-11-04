package vn.minerva.travinh.func.passport.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.PassportResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class LoginUseCase constructor(useCaseExecution: UseCaseExecution) : UseCase<PassportRequest, PassportResponse>(useCaseExecution) {
    //Đây là use case. mình có thể tạo nhiều thằng giống vầy để xử lý nhiều case riêng biệt.
    override fun buildUseCaseObservable(input: PassportRequest): Observable<PassportResponse> {
        return UserNetworkRepositoryIml().login(loginRequest = input)  //retrofit để call api
    }
}