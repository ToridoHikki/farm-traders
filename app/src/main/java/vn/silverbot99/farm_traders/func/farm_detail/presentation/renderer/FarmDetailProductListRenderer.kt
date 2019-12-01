package vn.silverbot99.farm_traders.func.farm_detail.presentation.renderer

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_product_list_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmDetailProductListItemModel

class FarmDetailProductListRenderer(mvpActivity: MvpActivity) : ViewRenderer<FarmDetailProductListItemModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_product_list_vholder

    override fun getModelClass(): Class<FarmDetailProductListItemModel> = FarmDetailProductListItemModel::class.java

    override fun bindView(model: FarmDetailProductListItemModel, viewRoot: View) {
        Glide.with(context).load(model.photo).into(viewRoot.ivProduct)
        viewRoot.tvNameProduct.text = model.name
        viewRoot.tvPrice.text = "Giá thị trường: ${model.price}"
        Log.d("farmProduct","Render")
    }
}