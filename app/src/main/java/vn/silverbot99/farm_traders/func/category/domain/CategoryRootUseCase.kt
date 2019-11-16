package vn.minerva.travinh.func.medical.domain

import io.reactivex.Observable
import vn.silverbot99.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.silverbot99.core.base.domain.interactor.UseCase
import vn.silverbot99.core.base.domain.interactor.UseCaseExecution
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse

class CategoryRootUseCase(useCaseExecution: UseCaseExecution):
    UseCase<String, CategoryRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: String): Observable<Output> {
        return Observable.create<Output>{emitter ->
            val categoryResponse =  CategoryUseCase(AndroidUseCaseExecution()).execute(input = input)
            val output = Output(categoryResponse = categoryResponse)
            emitter.onNext(output)
            emitter.onComplete()
        }
    }
    class Output(var categoryResponse: CategoriesResponse)
}