package vn.silverbot99.farm_traders.func.splash.domain

import io.reactivex.Observable
import vn.silverbot99.core.base.domain.interactor.UseCase
import vn.silverbot99.core.base.domain.interactor.UseCaseExecution
import vn.silverbot99.farm_traders.app.data.network.response.PassportResponse
import vn.silverbot99.farm_traders.app.domain.impl.UserNetworkRepositoryIml

class ReLoginUseCase constructor(useCaseExecution: UseCaseExecution) : UseCase<String, PassportResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: String): Observable<PassportResponse> {
        return UserNetworkRepositoryIml().reLogin()  //retrofit để call api
    }
}