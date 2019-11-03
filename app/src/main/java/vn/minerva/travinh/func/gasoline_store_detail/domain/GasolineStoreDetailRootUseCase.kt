package vn.minerva.travinh.func.gasoline_store_detail.domain

import io.reactivex.Observable
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.GasolineStoreDetailRequest
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse

class GasolineStoreDetailRootUseCase(useCaseExecution: UseCaseExecution):
    UseCase<GasolineStoreDetailRootUseCase.Input, GasolineStoreDetailRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output>{ emitter ->
            val gasolineStoreDetailResponse =  GasolineStoreDetailUseCase(AndroidUseCaseExecution()).execute(input = input.requestBody)
            val output = Output(gasolineStoreDetailResponse = gasolineStoreDetailResponse)
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    class Input(var requestBody: GasolineStoreDetailRequest)

    class Output(var gasolineStoreDetailResponse: GasolineStoreDetailResponse)
}