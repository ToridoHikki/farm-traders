package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator

class LocationFarmNearestPresenter(val screenNavigator: AndroidScreenNavigator) : LocationFarmNearestContract.Presenter(){
//
//    private var useCaseTask: UseCaseTask? = null
//    private var medicalDetailRootUseCase = MedicalRootUseCase(AndroidUseCaseExecution())
//    private var gasolineStoreRootUseCase = GasolineStoreRootUseCase(AndroidUseCaseExecution())
//    override fun getGasStationList(page: Int) {
//        if (page == -1) {
//            view?.showLoading()
//        }
//
//        val requestBody = GasolineStoreRequest(
//            page = page
//        )
//
//        useCaseTask?.cancel()
//        useCaseTask = gasolineStoreRootUseCase.executeAsync(object : ResultListener<GasolineStoreRootUseCase.Output> {
//            override fun success(data: GasolineStoreRootUseCase.Output) {
//                if (data.gasolineStoreResponse.success) {
//                    Log.i("LocationData","Location Get GasolineStoreResponse Success")
//
//                    view?.showDetailInfo(LocationGasolineMapper().map(data.gasolineStoreResponse))
//                } else {
//                    view?.showError("Error")
//                }
//            }
//
//            override fun fail(errorCode: Int, msgError: String) {
//                view?.showError("$errorCode - $msgError")
//            }
//
//            override fun done() {
//                view?.hideLoading()
//            }
//        },
//            GasolineStoreRootUseCase.Input(requestBody))
//
//    }
//
//    override fun getMedicalList(/*latitude: Long, longitude: Long,*/ page: Int) {
//        if (page == 1) {
//            view?.showLoading()
//        }
//
//        val requestBody = MedicalRequest(
//            page = page,
//            latitude = 9.947213700426019,
//            longitude = 106.33966710146512
//        )
//
//        useCaseTask?.cancel()
//        useCaseTask = medicalDetailRootUseCase.executeAsync(object : ResultListener<MedicalRootUseCase.Output> {
//            override fun success(data: MedicalRootUseCase.Output) {
//                if (data.medicalResponse.success) {
//                    Log.i("LocationData","Location Get MedicalResponse Success")
//                    view?.showDetailInfo(LocationMedicalMapper().map(data.medicalResponse))
//                } else {
//                    view?.showError("Error")
//                }
//            }
//
//            override fun fail(errorCode: Int, msgError: String) {
//                view?.showError("$errorCode - $msgError")
//            }
//
//            override fun done() {
//                view?.hideLoading()
//            }
//        },
//            MedicalRootUseCase.Input(requestBody))
//    }
}