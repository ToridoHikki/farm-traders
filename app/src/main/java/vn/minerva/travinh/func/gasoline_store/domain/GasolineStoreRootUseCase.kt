package vn.minerva.travinh.func.gasoline_store.domain

import io.reactivex.Observable
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.GasolineStoreRequest
import vn.minerva.travinh.app.data.network.response.GasolineStoreResponse

class GasolineStoreRootUseCase(useCaseExecution: UseCaseExecution):
    UseCase<GasolineStoreRootUseCase.Input, GasolineStoreRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output>{emitter ->
            val gasolineStoreResponse =  GasolineStoreUseCase(AndroidUseCaseExecution()).execute(input = input.requestBody)
            val output = Output(gasolineStoreResponse = gasolineStoreResponse)
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    class Input(var requestBody: GasolineStoreRequest)

    class Output(var gasolineStoreResponse: GasolineStoreResponse)
}