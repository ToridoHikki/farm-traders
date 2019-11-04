package vn.minerva.travinh.func.medical_detail.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.base.domain.mapper.Mapper
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse
import vn.minerva.travinh.func.medical_detail.presentation.model.MedicalDetailImageListViewModel


class MedicalDetailImageListMapper: Mapper<MedicalDetailResponse, MutableList<ViewModel>> {
    override fun map(input: MedicalDetailResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.medicalImageList.map{
            list.add(
                MedicalDetailImageListViewModel(
                    imageType = it.imageType.getValueOrDefaultIsEmpty(),
                    imageUrl = it.imageUrl.getValueOrDefaultIsEmpty()
                )
            )
        }
        return list
    }
}