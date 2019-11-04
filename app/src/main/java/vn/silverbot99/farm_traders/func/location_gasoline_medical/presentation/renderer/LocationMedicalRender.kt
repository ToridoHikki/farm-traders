package vn.silverbot99.farm_traders.func.location_gasoline_medical.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_medical_list_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.location_gasoline_medical.presentation.model.LocationMedicalItemModel

class LocationMedicalRender(mvpActivity: MvpActivity) : ViewRenderer<LocationMedicalItemModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_medical_list_vholder

    override fun getModelClass(): Class<LocationMedicalItemModel> = LocationMedicalItemModel::class.java

    override fun bindView(model: LocationMedicalItemModel, viewRoot: View) {
        Glide.with(context).load(model.medical_thumbnail).into(viewRoot.ivMedical)
        viewRoot.tvName.text = model.medicalName
        viewRoot.tvAddress.text = model.medicalAddress
        if(model.medicalMobile.equals(null)||model.medicalMobile.equals("")){
            viewRoot.tvPhone.visibility = View.GONE
        }
        else{
            viewRoot.tvPhone.text = model.medicalMobile
        }
        viewRoot.tvDate.text = model.nextAccreditationDate
    }

}