package vn.minerva.travinh.func.medical_detail.domain

import io.reactivex.Observable
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.MedicalDetailRequest
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse

class MedicalDetailRootUseCase (useCaseExecution: UseCaseExecution):
    UseCase<MedicalDetailRootUseCase.Input, MedicalDetailRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output>{ emitter ->
            val medicalDetailResponse =  MedicalDetailUseCase(AndroidUseCaseExecution()).execute(input = input.requestBody)
            val output = Output(medicalDetailResponse = medicalDetailResponse)
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    class Input(var requestBody: MedicalDetailRequest)

    class Output(var medicalDetailResponse: MedicalDetailResponse)
}