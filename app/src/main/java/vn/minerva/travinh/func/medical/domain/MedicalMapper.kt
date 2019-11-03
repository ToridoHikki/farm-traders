package vn.minerva.travinh.func.medical.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.base.domain.mapper.Mapper
import vn.minerva.travinh.app.data.network.response.MedicalResponse
import vn.minerva.travinh.func.medical.presentation.model.MedicalViewModel

class MedicalMapper :Mapper<MedicalResponse,MutableList<ViewModel>>{
    override fun map(input: MedicalResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.medicalList.map{
            list.add(MedicalViewModel(
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

            ))
        }
        return list
    }

}