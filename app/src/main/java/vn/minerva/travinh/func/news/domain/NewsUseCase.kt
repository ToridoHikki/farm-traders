package vn.minerva.travinh.func.news.domain

import io.reactivex.Observable
import vn.minerva.core.base.domain.interactor.UseCase
import vn.minerva.core.base.domain.interactor.UseCaseExecution
import vn.minerva.travinh.app.data.network.request.NewsRequest
import vn.minerva.travinh.app.data.network.response.NewsResponse
import vn.minerva.travinh.app.domain.impl.UserNetworkRepositoryIml

class NewsUseCase (useCaseExecution: UseCaseExecution):
    UseCase<NewsRequest, NewsResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: NewsRequest): Observable<NewsResponse> {
        return UserNetworkRepositoryIml().getNews(input)
    }
}