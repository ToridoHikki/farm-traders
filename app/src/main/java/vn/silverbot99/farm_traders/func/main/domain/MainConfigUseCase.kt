package vn.silverbot99.farm_traders.func.main.domain

import io.reactivex.Observable
import vn.silverbot99.core.base.domain.interactor.UseCase
import vn.silverbot99.core.base.domain.interactor.UseCaseExecution

class MainConfigUseCase(useCaseExecution: UseCaseExecution) :
    UseCase<MainConfigUseCase.Input, MainConfigUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output> { emitter ->

            var success = true
            var detail = ""
            val output = Output(
                success = success, detail = detail
            )
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    data class Input(var id: String)
    data class Output(var success: Boolean, var detail: String)
}
