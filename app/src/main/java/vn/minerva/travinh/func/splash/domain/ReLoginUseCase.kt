package vn.minerva.travinh.func.splash.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.response.PassportResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class ReLoginUseCase constructor(useCaseExecution: UseCaseExecution) : UseCase<String, PassportResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: String): Observable<PassportResponse> {
        return UserNetworkRepositoryIml().reLogin()  //retrofit để call api
    }
}