package vn.minerva.travinh.func.news.domain

import io.reactivex.Observable
import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.NewsRequest
import vn.minerva.travinh.app.data.network.response.NewsResponse

class NewsRootUseCase(useCaseExecution: UseCaseExecution):
    UseCase<NewsRootUseCase.Input, NewsRootUseCase.Output>(useCaseExecution) {
    override fun buildUseCaseObservable(input: Input): Observable<Output> {
        return Observable.create<Output>{ emitter ->
            val newsResponse =  NewsUseCase(AndroidUseCaseExecution()).execute(input = input.requestBody)
            val output = Output(newsResponse = newsResponse )
            emitter.onNext(output)
            emitter.onComplete()
        }
    }

    class Input(var requestBody: NewsRequest)

    class Output(var newsResponse: NewsResponse)
}