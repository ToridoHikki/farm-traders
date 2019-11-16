package vn.silverbot99.farm_traders.func.category.presentation

import vn.minerva.travinh.func.medical.domain.CategoryMapper
import vn.minerva.travinh.func.medical.domain.CategoryRootUseCase
import vn.silverbot99.core.app.domain.excecutor.AndroidUseCaseExecution
import vn.silverbot99.core.base.domain.interactor.ResultListener
import vn.silverbot99.core.base.domain.interactor.UseCaseTask

class CategoryPresenter:CategoryContract.Presenter() {
    private var useCaseTask: UseCaseTask? = null
    private var categoryRootUseCaseTask = CategoryRootUseCase(AndroidUseCaseExecution())

    override fun gotoProductList() {

    }
    override fun getCataloge() {
        useCaseTask?.cancel()
        useCaseTask = categoryRootUseCaseTask.executeAsync(object : ResultListener<CategoryRootUseCase.Output> {
            override fun success(data: CategoryRootUseCase.Output) {
                /*if (data.categoryResponse.success) {
                    view?.showMedicalDetail(MedicalMapper().map(data.medicalResponse),data.medicalResponse.total)
                } else {
                    view?.showError("Error")
                }*/
                if (!data.categoryResponse.categoriesList.isNullOrEmpty()){
                    view?.showDetailInfo(CategoryMapper().map(data.categoryResponse))
                }
                else{
                    view?.showError("Error in load Data")
                }
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError("$errorCode - $msgError")
            }

            override fun done() {
                view?.hideLoading()
            }
        },"")

    }
}