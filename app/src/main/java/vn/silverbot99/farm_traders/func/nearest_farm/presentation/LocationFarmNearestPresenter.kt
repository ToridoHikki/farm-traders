package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import android.content.ContentValues
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.app.data.network.response.LocationFarmNearestResponse
import vn.silverbot99.farm_traders.app.network.ApiInterfac
import vn.silverbot99.farm_traders.app.network.CustomApiClient
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.domain.LocationFarmNearestMapper
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class LocationFarmNearestPresenter(val screenNavigator: AndroidScreenNavigator) : LocationFarmNearestContract.Presenter(){
    override fun gotoFarmDetail(farm : LocationFarmItemModel/*farmName: String, farmId: String, farmPhoto: String */) {
        //screenNavigator.gotoFarmDetailScreen(farmName, farmI)
        screenNavigator.gotoFarmDetailScreen(farm)
    }

    override fun getFarmList() {
        view?.showLoading()
        val apiService = CustomApiClient.getClient().create(ApiInterfac.ApiInterface::class.java)

        val call = apiService.farms
        call.enqueue(object : Callback<LocationFarmNearestResponse> {
            override fun onResponse(call: Call<LocationFarmNearestResponse>, response: Response<LocationFarmNearestResponse>) {
                if (response.isSuccessful){
                    val categories: LocationFarmNearestResponse? = response.body()
                    categories?.let {
                        Log.d("response","okhttp: ${it.farms}")
                        view?.showDetailInfo(LocationFarmNearestMapper().map(it))
                        view?.hideLoading()
                    }
                }
                else{
                    view?.showError(response.message())
                }
            }

            override fun onFailure(call: Call<LocationFarmNearestResponse>, t: Throwable) {
                Log.e("response", t.toString())
                view?.showError(t.toString());
                view?.hideLoading()
            }
        })
    }

}