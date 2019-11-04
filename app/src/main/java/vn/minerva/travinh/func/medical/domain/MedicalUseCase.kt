package vn.minerva.travinh.func.medical.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.MedicalRequest
import vn.minerva.travinh.app.data.network.response.MedicalResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class MedicalUseCase(useCaseExecution: UseCaseExecution):
    UseCase<MedicalRequest, MedicalResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: MedicalRequest): Observable<MedicalResponse> {
        return UserNetworkRepositoryIml().getMedicalDetail(input)
    }
}