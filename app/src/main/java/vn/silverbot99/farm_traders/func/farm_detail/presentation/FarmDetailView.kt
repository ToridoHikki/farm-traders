package vn.silverbot99.farm_traders.func.farm_detail.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_farm_detail.view.*
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import vn.silverbot99.core.base.presentation.mvp.android.list.ListViewMvp
import vn.silverbot99.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel
import android.location.Address
import android.support.design.widget.AppBarLayout
import android.location.Geocoder
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmDetailProductListItemModel
import vn.silverbot99.farm_traders.func.farm_detail.presentation.renderer.FarmDetailProductListRenderer
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel
import java.util.*


class FarmDetailView (mvpActivity: MvpActivity, viewCreator: ViewCreator,val farm: LocationFarmItemModel/*val farmName: String,val farmId: String,val farmPhoto: String */) : AndroidMvpView(mvpActivity,viewCreator),
    FarmDetailContract.View {


    override fun showFarmerDetail(data: FarmerResponse) {
        view.tvFarmerName.text = data.farmer.farmerName
        view.tvPhone.text = data.farmer.phoneNumber
    }

    private var listViewMvp: ListViewMvp? = null
    private var appBarExpanded: Boolean = true
    private var listData: MutableList<ViewModel> = mutableListOf()
    private val mPresenter = FarmDetailPresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val renderInput = GridRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = GridRenderConfigFactory.Orientation.VERTICAL,
        spanCount = 2,
        spanSizeLookup = getSpanSizeLookup()
    )
    private val renderConfig = GridRenderConfigFactory(renderInput).create()
    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (listViewMvp?.items?.size == 2) {
                    return 1
                }
                return 2
            }
        }
    }

    override fun showDetailInfo(data: List<ViewModel>) {
        if(data.isNotEmpty()){
            listData.addAll(data)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
        Log.d("farmProduct","listData: $listData")
    }

    override fun initCreateView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvProductListofFarmDetail,renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(FarmDetailProductListRenderer(mvpActivity))
        Glide.with(mvpActivity).load(farm.photo).into(view.ivFarmDetail)
        view.tvFarmName.text = farm.name

        getTitleFromLatLon(farm.lat,farm.long)
        val toolbar = view.toolbarFarm
        mvpActivity.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { mvpActivity.onBackPressed()}

        initAppBar()
        listViewMvp?.setOnItemRvClickedListener(mOnClickCategoryItem)

    }
    private fun getTitleFromLatLon(lat: Double, lon: Double): String{
        val addresses: List<Address>
        val geocoder: Geocoder =  Geocoder(mvpActivity, Locale.getDefault())
        addresses = geocoder.getFromLocation(lat, lon, 1)
        val location: String = addresses.get(0).getAddressLine(0)
        Log.d("address","getTitleFromLatLon: $location")
        view.tvAddress.text = location
        return addresses.toString()

    }
    /*    private val mOnClickMessageItem = (object : View.OnClickListener{
            override fun onClick(v: View?) {s
                EmptyFragment()
            }
        })*/
    private fun initAppBar() {
        view.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            @SuppressLint("RestrictedApi")
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if(Math.abs(p1) > 200){
                    appBarExpanded = false;
                    view.llFarmName.visibility = View.GONE
                    view.collapsing.title = farm.name
                }else{
                    appBarExpanded = true;
                    view.llFarmName.visibility = View.VISIBLE

                }
            }

        })
    }
    private val mOnClickCategoryItem: OnItemRvClickedListener<ViewModel> = object :
        OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            runWithCheckMultiTouch("ListMenuView_click_item", object : OnActionNotify {
                override fun onActionNotify() {
                    if (dataItem is FarmDetailProductListItemModel) {
                        Log.d("item","onItemClicked ${dataItem.name}")
                        //mPresenter.gotoProductList(dataItem.categoryId)
                        mPresenter.gotoProductDetail(convertObject(dataItem))
                    }
                }
            })
        }
    }
    private fun convertObject(farmDetailProductListItemModel: FarmDetailProductListItemModel): ProductListItemModel{
        val productListItemModel: ProductListItemModel = ProductListItemModel()
        farmDetailProductListItemModel.let {
            productListItemModel.name = it.name
            productListItemModel.price = it.price
            productListItemModel.categoryId = it.categoryId
            productListItemModel.description = it.description
            productListItemModel.farmId = it.farmId
            productListItemModel.productId = it.productId
            productListItemModel.photo = it.photo
        }
        return productListItemModel
    }
    override fun loadProductList() {
        mPresenter.getProductListOfFarm(farmId = farm.farmId)
    }
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
        mPresenter.getFarmerbyFarmId(farm.farmId)
        mPresenter.getProductListOfFarm(farm.farmId)
    }


    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_farm_detail, context, viewGroup)

}