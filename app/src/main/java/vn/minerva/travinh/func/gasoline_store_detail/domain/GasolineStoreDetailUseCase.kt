package vn.minerva.travinh.func.gasoline_store_detail.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.GasolineStoreDetailRequest
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class GasolineStoreDetailUseCase (useCaseExecution: UseCaseExecution):
    UseCase<GasolineStoreDetailRequest, GasolineStoreDetailResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: GasolineStoreDetailRequest): Observable<GasolineStoreDetailResponse> {
        return UserNetworkRepositoryIml().getGasolineStore(input)
    }
}