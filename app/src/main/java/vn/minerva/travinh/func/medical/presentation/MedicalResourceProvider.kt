package vn.minerva.travinh.func.medical.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class MedicalResourceProvider : AndroidResourceProvider(){
    fun getTitle(): String{
        return resourceManager.getString(R.string.medical)
    }
}