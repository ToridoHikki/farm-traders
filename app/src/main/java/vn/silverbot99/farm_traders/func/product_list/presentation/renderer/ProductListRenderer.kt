package vn.silverbot99.farm_traders.func.product_list.presentation.renderer

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout_product_list_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

class ProductListRenderer (mvpActivity: MvpActivity) : ViewRenderer<ProductListItemModel>(mvpActivity){
    override fun getLayoutId(): Int = R.layout.item_layout_product_list_vholder

    override fun getModelClass(): Class<ProductListItemModel> = ProductListItemModel::class.java

    override fun bindView(model: ProductListItemModel, viewRoot: View) {
        Glide.with(context).load(model.photo).into(viewRoot.ivProduct)
        viewRoot.tvNameProduct.text = model.name
        viewRoot.tvDescription.text = model.description
        viewRoot.tvPrice.text = model.price
    }
}