package vn.silverbot99.farm_traders.func.nearest_farm.domain

import android.util.Log
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.MedicalResponse
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class LocationMedicalMapper/* : Mapper<MedicalResponse, MutableList<ViewModel>>*/ {/*
    override fun map(input: MedicalResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.farList.map{
            list.add(
                LocationFarmItemModel(
                    farmId = it.farmId.getValueOrDefaultIsZero(),
                    farmType = it.farmType.getValueOrDefaultIsEmpty(),
                    farmName = it.farmName.getValueOrDefaultIsEmpty(),
                    farmOwner = it.farmOwner.getValueOrDefaultIsEmpty(),
                    farmMobile = it.farmMoblie.getValueOrDefaultIsEmpty(),
                    farmAddress = it.farmAddress.getValueOrDefaultIsEmpty(),
                    farmLap = it.farmLap.getValueOrDefaultIsZero(),
                    farmLon = it.farmLon.getValueOrDefaultIsZero(),
                    farmDistrictId = it.farmDistrictId.getValueOrDefaultIsZero(),
                    farmDistrictName = it.farmDistrictName.getValueOrDefaultIsEmpty(),
                    farmWardId = it.farmWardId.getValueOrDefaultIsZero(),
                    farmWardName = it.farmWardName.getValueOrDefaultIsEmpty(),
                    farm_thumbnail = it.farm_thumbnail.getValueOrDefaultIsEmpty(),
                    nextAccreditationDate = it.NextAccreditationDate.getValueOrDefaultIsEmpty()

                )
            )
        }
        Log.i("LocationData","Map Medical data Success")

        return list
    }
*/
}