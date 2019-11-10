package vn.silverbot99.farm_traders.func.nearest_farm.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
//import kotlinx.android.synthetic.main.item_layout_farm_list_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class LocationMedicalRender/*(mvpActivity: MvpActivity) : ViewRenderer<LocationFarmItemModel>(mvpActivity)*/{
    /*override fun getLayoutId(): Int = R.layout.item_layout_farm_list_vholder

    override fun getModelClass(): Class<LocationFarmItemModel> = LocationFarmItemModel::class.java

    override fun bindView(model: LocationFarmItemModel, viewRoot: View) {
        Glide.with(context).load(model.farm_thumbnail).into(viewRoot.ivMedical)
        viewRoot.tvName.text = model.farmName
        viewRoot.tvAddress.text = model.farmAddress
        if(model.farmMobile.equals(null)||model.farmMobile.equals("")){
            viewRoot.tvPhone.visibility = View.GONE
        }
        else{
            viewRoot.tvPhone.text = model.farmMobile
        }
        viewRoot.tvDate.text = model.nextAccreditationDate
    }
*/
}