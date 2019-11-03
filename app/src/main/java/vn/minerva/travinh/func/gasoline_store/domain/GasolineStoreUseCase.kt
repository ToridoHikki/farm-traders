package vn.minerva.travinh.func.gasoline_store.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.GasolineStoreRequest
import vn.minerva.travinh.app.data.network.response.GasolineStoreResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class GasolineStoreUseCase(useCaseExecution: UseCaseExecution):
    UseCase<GasolineStoreRequest, GasolineStoreResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: GasolineStoreRequest): Observable<GasolineStoreResponse> {
        return UserNetworkRepositoryIml().getGasolineStoreDetail(input)
    }
}