package vn.silverbot99.farm_traders.func.farm_detail.presentation

import android.content.ContentValues
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.app.data.network.response.ProductListResponse
import vn.silverbot99.farm_traders.app.network.ApiInterfac
import vn.silverbot99.farm_traders.app.network.CustomApiClient
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.farm_detail.domain.FarmDetailProductListMapper
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmDetailProductListItemModel
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

class FarmDetailPresenter(val screenNavigator: AndroidScreenNavigator): FarmDetailContract.Presenter() {
    override fun gotoProductDetail(productListItemModel: ProductListItemModel) {
        screenNavigator.gotoProductDetailScreen(productListItemModel)
    }

    override fun getProductListOfFarm(farmId: String) {
        val apiService = CustomApiClient.getClient().create(ApiInterfac.ApiInterface::class.java)

        val call = apiService.getProducts(farmId,null)
        call.enqueue(object : Callback<ProductListResponse> {
            override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                if (response.isSuccessful){
                    val categories: ProductListResponse? = response.body()
                    categories?.let {
                        Log.d("farmProduct", "okhttp: ${it.productList}")
                        if(it.productList.isEmpty()){
                            view?.loadProductList()
                        }
                        else{
                            view?.hideLoading()
                            view?.showDetailInfo(FarmDetailProductListMapper().map(it))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e(ContentValues.TAG, t.toString())
                view?.showError(t.toString());
            }
        })
    }

    override fun getFarmerbyFarmId(farmId: String) {
        view?.showLoading()
        val apiService = CustomApiClient.getClient().create(ApiInterfac.ApiInterface::class.java)

        val call = apiService.getFarmerByFarmId(farmId)
        call.enqueue(object : Callback<FarmerResponse> {
            override fun onResponse(call: Call<FarmerResponse>, response: Response<FarmerResponse>) {
                if (response.isSuccessful){
                    val categories: FarmerResponse? = response.body()
                    categories?.let {
                        Log.d("response", "okhttp: ${it.farmer}")
                        view?.showFarmerDetail(it)
                        view?.hideLoading()
                    }
                }
            }

            override fun onFailure(call: Call<FarmerResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e(ContentValues.TAG, t.toString())
                view?.hideLoading()
                view?.showError(t.toString());
            }
        })
    }
}