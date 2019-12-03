package vn.silverbot99.farm_traders.func.product_detail.presentation

import android.app.Dialog
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.context.showAlert
import kotlinex.mvpactivity.showErrorAlert
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_product_detail.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.data.network.response.FarmResponse
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.app.data.network.response.LocationFarmNearestResponse
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmerItemModel
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel
import java.util.*

class ProductDetailView(mvpActivity: MvpActivity, viewCreator: ViewCreator, val product: ProductListItemModel) : AndroidMvpView(mvpActivity,viewCreator),
    ProductDetailContract.View {
    override fun showFarmDetail(data: MutableList<ViewModel>) {
        data.map {
            if (it is LocationFarmItemModel){
                farmItemModel.farmId = it.farmId
                farmItemModel.name = it.name
                farmItemModel.photo = it.photo
                farmItemModel.long = it.long
                farmItemModel.lat = it.lat
                getTitleFromLatLon(it.lat,it.long)
            }
        }
    }


    override fun showFarmerDetail(data: FarmerResponse) {
        view.tvProductDetailFarmerName.text = data.farmer.farmerName
        mPresenter.getFarmInfo(data.farmer.farmerId)
        phoneFarmer = data.farmer.phoneNumber
    }


    private val mPresenter = ProductDetailPresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private var farmItemModel = LocationFarmItemModel()
    private var phoneFarmer: String = ""

    override fun initCreateView() {

        Glide.with(mvpActivity).load(product.photo).into(view.ivProductDetail)
        view.tvProductDetailName.text = product.name
        view.tvProductDetailPrice.text = "Giá thị trường: ${product.price}"
        view.tvProductDetailDescription.text = product.description
        val toolbar = view.toolbarProductDetail
        mvpActivity.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { mvpActivity.onBackPressed()}

        view.btnContact.setOnClickListener(onClickContact)
        view.btnDirection.setOnClickListener(onClickLocation)


    }
    private fun getTitleFromLatLon(lat: Double, lon: Double): String{
        val addresses: List<Address>
        val geocoder: Geocoder =  Geocoder(mvpActivity, Locale.getDefault())
        addresses = geocoder.getFromLocation(lat, lon, 1)
        val location: String = addresses.get(0).getAddressLine(0)
        Log.d("address","getTitleFromLatLon: $location")
        view.tvProductDetailLocation.text = location
        return addresses.toString()

    }
    private val onClickContact: View.OnClickListener = View.OnClickListener {
        mvpActivity.showAlert("Chọn cách thức liên lạc","","Gọi điện","Nhắn tin",
            object: OnActionNotify{
                override fun onActionNotify() {
                    if (phoneFarmer != ""){
                        mPresenter.calltoFarmer(phoneFarmer)
                    }
                }

            },
            object: OnActionNotify{
                override fun onActionNotify() {
                    if (phoneFarmer != ""){
                        mPresenter.messToFarmer(phoneFarmer)
                    }                }

            })
    }
    private val onClickLocation: View.OnClickListener = View.OnClickListener {
        farmItemModel.let {
            mPresenter.gotoMap(it)
        }
    }

/*    private val mOnClickCategoryItem: OnItemRvClickedListener<ViewModel> = object :
        OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify {
                override fun onActionNotify() {
                    if (dataItem is FarmDetailProductListItemModel) {
                        Log.d("item","onItemClicked ${dataItem.name}")
                        //mPresenter.gotoProductList(dataItem.categoryId)
                    }
                }
            })
        }
    }*/

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun initData() {
        super.initData()
        mPresenter.getFarmerInfo(product.farmId)

    }


    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_product_detail, context, viewGroup)

}