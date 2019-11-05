package vn.silverbot99.farm_traders.func.nearest_farm.domain

import android.util.Log
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.core.base.domain.mapper.Mapper
import vn.silverbot99.farm_traders.app.data.network.response.MedicalResponse
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationMedicalItemModel

class LocationMedicalMapper : Mapper<MedicalResponse, MutableList<ViewModel>> {
    override fun map(input: MedicalResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.medicalList.map{
            list.add(
                LocationMedicalItemModel(
                    medicalId = it.medicalId.getValueOrDefaultIsZero(),
                    medicalType = it.medicalType.getValueOrDefaultIsEmpty(),
                    medicalName = it.medicalName.getValueOrDefaultIsEmpty(),
                    medicalOwner = it.medicalOwner.getValueOrDefaultIsEmpty(),
                    medicalMobile = it.medicalMoblie.getValueOrDefaultIsEmpty(),
                    medicalAddress = it.medicalAddress.getValueOrDefaultIsEmpty(),
                    medicalLap = it.medicalLap.getValueOrDefaultIsZero(),
                    medicalLon = it.medicalLon.getValueOrDefaultIsZero(),
                    medicalDistrictId = it.medicalDistrictId.getValueOrDefaultIsZero(),
                    medicalDistrictName = it.medicalDistrictName.getValueOrDefaultIsEmpty(),
                    medicalWardId = it.medicalWardId.getValueOrDefaultIsZero(),
                    medicalWardName = it.medicalWardName.getValueOrDefaultIsEmpty(),
                    medical_thumbnail = it.medical_thumbnail.getValueOrDefaultIsEmpty(),
                    nextAccreditationDate = it.NextAccreditationDate.getValueOrDefaultIsEmpty()

                )
            )
        }
        Log.i("LocationData","Map Medical data Success")

        return list
    }

}