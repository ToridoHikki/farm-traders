package vn.silverbot99.farm_traders.func.splash.domain

import io.reactivex.Observable
import vn.silverbot99.core.base.domain.interactor.UseCase
import vn.silverbot99.core.base.domain.interactor.UseCaseExecution
import vn.silverbot99.farm_traders.app.data.network.response.AppVersionResponse

class CheckVersionAppUseCase constructor(useCaseExecution: UseCaseExecution) : UseCase<String, AppVersionResponse>(useCaseExecution) {
    override fun buildUseCaseObservable(input: String): Observable<AppVersionResponse> {
//        return UserNetworkRepositoryIml().checkVersion()
        return  Observable.just(
            AppVersionResponse(
                version = "0",
                forceFlag = false,
                googlePlay = "",
                versionApp = ""
            ).apply {
                success = true
            }
        )
    }
}