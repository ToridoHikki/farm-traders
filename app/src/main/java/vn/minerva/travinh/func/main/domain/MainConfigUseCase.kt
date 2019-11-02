package vn.minerva.travinh.func.main.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution

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
