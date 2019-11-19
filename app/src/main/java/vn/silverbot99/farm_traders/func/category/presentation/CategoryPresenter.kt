package vn.silverbot99.farm_traders.func.category.presentation

import android.content.ContentValues.TAG
import android.support.v4.app.Fragment
import vn.minerva.travinh.func.medical.domain.CategoryMapper
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse
import vn.silverbot99.farm_traders.app.network.ApiInterfac.ApiInterface
import vn.silverbot99.farm_traders.app.network.CustomApiClient
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.empty.EmptyFragment


class CategoryPresenter(val screenNavigator: AndroidScreenNavigator):CategoryContract.Presenter() {


    override fun getCategoryList() {
        view?.showLoading()
        val apiService = CustomApiClient.getClient().create(ApiInterface::class.java)

        val call = apiService.catalogies
        call.enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                if (response.isSuccessful){
                    val categories: CategoriesResponse? = response.body()
                    categories?.let {
                        Log.d("response","okhttp: ${it.categoriesList}")
                        view?.showDetailInfo(CategoryMapper().map(it))
                        view?.hideLoading()
                    }
                }
                else{
                    view?.showError(response.message())
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                view?.showError(t.toString());
                view?.hideLoading()
            }
        })
    }


    override fun gotoProductList(categoryId: String) {
        screenNavigator.gotoProductList(categoryId)
    }

}
/*
override fun getCataloge() {
    useCaseTask?.cancel()
    useCaseTask = categoryRootUseCaseTask.executeAsync(object : ResultListener<CategoryRootUseCase.Output> {
        override fun success(data: CategoryRootUseCase.Output) {
            */
/*if (data.categoryResponse.success) {
                view?.showMedicalDetail(MedicalMapper().map(data.medicalResponse),data.medicalResponse.total)
            } else {
                view?.showError("Error")
            }*//*

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
}*/
