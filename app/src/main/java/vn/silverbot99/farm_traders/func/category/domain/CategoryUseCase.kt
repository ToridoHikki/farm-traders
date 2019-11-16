package vn.minerva.travinh.func.medical.domain

import io.reactivex.Observable
import vn.silverbot99.core.base.domain.interactor.UseCase
import vn.silverbot99.core.base.domain.interactor.UseCaseExecution
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse
import vn.silverbot99.farm_traders.app.domain.impl.UserNetworkRepositoryIml


class CategoryUseCase constructor(useCaseExecution: UseCaseExecution) : UseCase<String, CategoriesResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: String): Observable<CategoriesResponse> {
        return UserNetworkRepositoryIml().getCatalogies()
    }
}