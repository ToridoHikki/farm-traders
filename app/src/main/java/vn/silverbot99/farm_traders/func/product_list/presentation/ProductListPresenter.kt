package vn.silverbot99.farm_traders.func.product_list.presentation

import android.content.ContentValues
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.app.data.network.response.ProductListResponse
import vn.silverbot99.farm_traders.app.network.ApiInterfac
import vn.silverbot99.farm_traders.app.network.CustomApiClient
import vn.silverbot99.farm_traders.func.product_list.domain.ProductListMapper

class ProductListPresenter: ProductListContract.Presenter() {
    override fun getProductList(categoryId: String) {
        val apiService = CustomApiClient.getClient().create(ApiInterfac.ApiInterface::class.java)

        val call = apiService.getProducts(null,categoryId)
        call.enqueue(object : Callback<ProductListResponse> {
            override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                if (response.isSuccessful){
                    val categories: ProductListResponse? = response.body()
                    categories?.let {
                        Log.d("response","categoryId: $categoryId, okhttp: ${it.productList}")
                        if(it.productList.isEmpty()){
                            view?.loadData()
                        }
                        else{
                            view?.hideLoading()
                            view?.showDetailInfo(ProductListMapper().map(it))
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

}