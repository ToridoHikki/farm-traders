package vn.minerva.travinh.func.medical_detail.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.MedicalDetailRequest
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class MedicalDetailUseCase (useCaseExecution: UseCaseExecution):
    UseCase<MedicalDetailRequest, MedicalDetailResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: MedicalDetailRequest): Observable<MedicalDetailResponse> {
        return UserNetworkRepositoryIml().getMedicalInfo(input)
    }
}