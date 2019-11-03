package vn.minerva.travinh.func.medical.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_medical_list_vholder.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.android.model.ViewRenderer
import vn.minerva.travinh.R
import vn.minerva.travinh.func.medical.presentation.model.MedicalViewModel

class MedicalRender(mvpActivity: MvpActivity) : ViewRenderer<MedicalViewModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_medical_list_vholder

    override fun getModelClass(): Class<MedicalViewModel> = MedicalViewModel::class.java

    override fun bindView(model: MedicalViewModel, viewRoot: View) {
        Glide.with(context).load(model.medical_thumbnail).into(viewRoot.ivMedical)
        viewRoot.tvName.text = model.medicalName
        viewRoot.tvAddress.text = model.medicalAddress
        viewRoot.tvPhone.text = model.medicalMobile
        viewRoot.tvDate.text = model.nextAccreditationDate
    }

}