package vn.minerva.travinh.func.medical.domain

import io.reactivex.Observable
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.MedicalRequest
import vn.minerva.travinh.app.data.network.response.MedicalResponse

class MedicalRootUseCase(useCaseExecution: UseCaseExecution):
    UseCase<MedicalRootUseCase.Input, MedicalRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output>{emitter ->
            val medicalResponse =  MedicalUseCase(AndroidUseCaseExecution()).execute(input = input.requestBody)
            val output = Output(medicalResponse = medicalResponse)
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    class Input(var requestBody: MedicalRequest)

    class Output(var medicalResponse: MedicalResponse)
}