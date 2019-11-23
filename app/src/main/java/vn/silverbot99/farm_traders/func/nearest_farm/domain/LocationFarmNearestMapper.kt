package vn.silverbot99.farm_traders.func.nearest_farm.domain

import android.util.Log
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.LocationFarmNearestResponse
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class LocationFarmNearestMapper : Mapper<LocationFarmNearestResponse, MutableList<ViewModel>> {
    override fun map(input: LocationFarmNearestResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.farms.map{
            list.add(
                LocationFarmItemModel(
                    name = it.name.getValueOrDefaultIsEmpty(),
                    farmId = it.farmId.getValueOrDefaultIsEmpty(),
                    photo = it.photo.getValueOrDefaultIsEmpty(),
                    lat = it.lat.getValueOrDefaultIsZero(),
                    long = it.long.getValueOrDefaultIsZero()
                )
            )
        }
        Log.i("LocationData", "Map data LocationFarmNearestResponse success")
        return list
    }

}