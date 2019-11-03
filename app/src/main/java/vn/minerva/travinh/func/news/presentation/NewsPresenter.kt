package vn.minerva.travinh.func.news.presentation

import vn.minerva.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.minerva.core.base.domain.interactor.ResultListener
import vn.minerva.core.base.domain.interactor.UseCaseTask
import vn.minerva.travinh.app.data.network.request.NewsRequest
import vn.minerva.travinh.app.presentation.navigation.ScreenNavigator
import vn.minerva.travinh.func.news.domain.NewsMapper
import vn.minerva.travinh.func.news.domain.NewsRootUseCase

class NewsPresenter(var screenNavigator: ScreenNavigator): NewsContract.Presenter() {
    private var useCaseTask: UseCaseTask? = null
    private var newsRootUseCase = NewsRootUseCase(AndroidUseCaseExecution())
    override fun getNewsList(page: Int) {
        if (page == 1) {
            view?.showLoading()
        }

        val requestBody = NewsRequest(
            page = page
        )

        useCaseTask?.cancel()
        useCaseTask = newsRootUseCase.executeAsync(object : ResultListener<NewsRootUseCase.Output> {
            override fun success(data: NewsRootUseCase.Output) {
                if (data.newsResponse.success) {
                    view?.showNewsList(NewsMapper().map(data.newsResponse))
                } else {
                    view?.showError("Error")
                }
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError("$errorCode - $msgError")
            }

            override fun done() {
                view?.hideLoading()
            }
        },
            NewsRootUseCase.Input(requestBody))
    }

    override fun gotoMainView() {
        screenNavigator.gotoMainActivity()
    }

}