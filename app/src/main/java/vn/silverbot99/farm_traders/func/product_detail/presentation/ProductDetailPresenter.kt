package vn.silverbot99.farm_traders.func.product_detail.presentation

import android.content.ContentValues
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.app.data.network.response.FarmResponse
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.app.data.network.response.LocationFarmNearestResponse
import vn.silverbot99.farm_traders.app.network.ApiInterfac
import vn.silverbot99.farm_traders.app.network.CustomApiClient
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.domain.LocationFarmNearestMapper
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class ProductDetailPresenter(val screenNavigator: AndroidScreenNavigator): ProductDetailContract.Presenter() {


    override fun gotoMap(locationFarmItemModel: LocationFarmItemModel) {
        screenNavigator.gotoMapScreen(locationFarmItemModel)
    }

    override fun calltoFarmer(farmerNumber: String) {
        screenNavigator.callToFarmer(farmerNumber)
    }

    override fun messToFarmer(farmerNumber: String) {
        screenNavigator.sendSMSToFarmer(farmerNumber)
    }

    override fun getFarmerInfo(farmId: String) {
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
    override fun getFarmInfo(farmerId: String) {
        view?.showLoading()
        val apiService = CustomApiClient.getClient().create(ApiInterfac.ApiInterface::class.java)

        val call = apiService.getFarmsByFarmerId(farmerId)
        call.enqueue(object : Callback<LocationFarmNearestResponse> {
            override fun onResponse(call: Call<LocationFarmNearestResponse>, response: Response<LocationFarmNearestResponse>) {
                if (response.isSuccessful){
                    val categories: LocationFarmNearestResponse? = response.body()
                    categories?.let {
                        Log.d("response", "okhttp: ${it}")
                        view?.showFarmDetail(LocationFarmNearestMapper().map(it))
                        view?.hideLoading()
                    }
                }
            }

            override fun onFailure(call: Call<LocationFarmNearestResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e("response", t.toString())
                view?.hideLoading()
                view?.showError(t.toString());
            }
        })
    }
}