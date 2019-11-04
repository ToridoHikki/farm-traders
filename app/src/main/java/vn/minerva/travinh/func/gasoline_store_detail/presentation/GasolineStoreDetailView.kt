package vn.minerva.travinh.func.gasoline_store_detail.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat.invalidateOptionsMenu
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_gasoline_store_detail.view.*
import kotlinx.android.synthetic.main.layout_medical_detail.view.*
import kotlinx.android.synthetic.main.layout_medical_gasstation_map.view.*
import vn.minerva.core.app.util.image.GlideImageHelper
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import vn.minerva.travinh.func.gasoline_store_detail.presentation.contact.GasolineStoreDetailContactView
import vn.minerva.travinh.func.gasoline_store_detail.presentation.map.GasolineStoreDetailMapView
import vn.minerva.travinh.func.gasoline_store_detail.presentation.model.GasolineStoreDetailImageListViewModel
import vn.minerva.travinh.func.medical_detail.presentation.model.MedicalDetailImageListViewModel

class GasolineStoreDetailView(mvpActivity: MvpActivity, viewCreator: ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), GasolineStoreDetailContract.View {
    override fun showImageList(data: List<ViewModel>) {
        listData.clear()
        if (data.isNotEmpty()) {
            listData.addAll(data)
        }

        if(listData.isEmpty()){
            var imageView: ImageView = ImageView(mvpActivity)
            imageView.scaleType = ImageView.ScaleType.CENTER
            Glide.with(mvpActivity).load(R.drawable.ima_gas_store).into(imageView)
            view.vlGasolineStore.addView(imageView)
        }
        else{
            listData.forEach {
                if(it is GasolineStoreDetailImageListViewModel){
                    Log.d("loadImageToViewFlipper","loadImageToViewFlipper: ${it.imageUrl}")
                    loadImageToViewFlipper(it.imageUrl)
                }
            }
        }
    }


    override fun showDetailToolbar(data: GasolineStoreDetailResponse) {
        view.tvStoreName.text = data.storeName
        view.tvStoreType.text = data.companyType
        view.collapsing.title = data.storeName
    }


    override fun initData() {
        super.initData()
        gasolineStoreDetailContactView!!.loadData()
       // mPresenter.getDetailToolbar(id)
        mPresenter.getDetailInfo(id)
    }
    private var appBarExpanded :Boolean = true
    private var gasolineStoreDetailContactView : GasolineStoreDetailContactView? = null
    private var gasolineStoreDetailMapView : GasolineStoreDetailMapView? = null
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = GasolineStoreDetailPresenter((AndroidScreenNavigator(mvpActivity)))
    private var listData: MutableList<ViewModel> = mutableListOf()


    private fun initTabBar() {
        view.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> {
                        showContactView()
                    }
                    1 -> {

                    }
                    2 -> {
                        showMapView()
                    }
                    3 -> {

                    }
                    4 -> {

                    }


                }
            }

        })
    }

    private fun showMapView() {
        addMainContentWithView(gasolineStoreDetailMapView)
        gasolineStoreDetailMapView?.startMvpView()
        gasolineStoreDetailMapView!!.loadData()
    }

    private fun showContactView() {
        addMainContentWithView(gasolineStoreDetailContactView)
        gasolineStoreDetailContactView?.startMvpView()
        gasolineStoreDetailContactView!!.loadData()
    }

    private fun addMainContentWithView(mvpView: AndroidMvpView?) {
        mvpView?.let {
            view.vgContentGasDetail.removeAllViews()
            view.vgContentGasDetail.addView(it?.createView())
            addLifeCycle(it)
            0
        }
    }
    private fun initContactView() {
        gasolineStoreDetailContactView = GasolineStoreDetailContactView(mvpActivity,GasolineStoreDetailContactView.ViewCreator(mvpActivity,null),id)
    }

    private fun initMapView() {
        gasolineStoreDetailMapView = GasolineStoreDetailMapView(mvpActivity,GasolineStoreDetailMapView.ViewCreator(mvpActivity,null),id)
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
        gasolineStoreDetailContactView?.startMvpView()
        gasolineStoreDetailMapView?.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
        gasolineStoreDetailContactView?.stopMvpView()
        gasolineStoreDetailMapView?.stopMvpView()
    }

        override fun initCreateView() {
            val toolbar = view.toolbarGasStoreDetail
            mvpActivity.setSupportActionBar(toolbar)
            mvpActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            mvpActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
            mvpActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbar.setNavigationOnClickListener { /*handleOnBackPress()*/ mvpActivity.onBackPressed()}

            view.faCamera.setOnClickListener{OnClickfaCameraListener()}
            initViewFlipper()
            initTabBar()
            initContactView()
            showContactView()
            initAppBar()
            initMapView()

        }

    private fun initViewFlipper() {
        //view.vlGasolineStore.startFlipping()
        var initialX: Float = 0f
        view.vlGasolineStore.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_DOWN -> {
                        initialX = event.getX()
                    }
                    MotionEvent.ACTION_UP ->{
                        var finalX: Float = event.getX()
                        if(initialX > finalX){
                            if(view.vlGasolineStore.displayedChild == listData.size){
                                view.vlGasolineStore.stopFlipping()
                            }
                            view.vlGasolineStore.showNext()
                        }else{
                            if(view.vlGasolineStore.displayedChild == 0 || view.vlGasolineStore.displayedChild == 1){
                                view.vlGasolineStore.stopFlipping()
                            }
                            view.vlGasolineStore.showPrevious()
                        }
                    }
                }
                return false
            }

        })
    }

    private fun loadImageToViewFlipper(image: String){
        var imageView: ImageView = ImageView(mvpActivity)
        imageView.scaleType = ImageView.ScaleType.CENTER
        Glide.with(mvpActivity).load(image).into(imageView)

        view.vlGasolineStore.addView(imageView)

        view.vlGasolineStore.setInAnimation(mvpActivity,android.R.anim.slide_in_left)
        view.vlGasolineStore.setInAnimation(mvpActivity,android.R.anim.slide_out_right)


        /*view.vlGasolineStore.setFlipInterval(2000)
        view.vlGasolineStore.isAutoStart = true
        view.vlGasolineStore.setInAnimation(mvpActivity,android.R.anim.slide_in_left)
        view.vlGasolineStore.setInAnimation(mvpActivity,android.R.anim.slide_out_right)*/


    }


    private fun OnClickfaCameraListener() {
        mPresenter.gotoLibrary()
    }

    private fun initAppBar() {
        view.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            @SuppressLint("RestrictedApi")
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if(Math.abs(p1) > 200){
                    appBarExpanded = false;
                    view.llstoreName.visibility = View.GONE
                    view.faCamera.visibility = View.GONE
                    view.vlGasolineStore.stopFlipping()
                }else{
                    appBarExpanded = true;
                    view.llstoreName.visibility = View.VISIBLE
                    view.faCamera.visibility = View.VISIBLE
                    view.vlGasolineStore.startFlipping()
                }
            }

        })
    }

    private fun handleOnBackPress() {
        mPresenter.goBackGasolineStoreList()
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_gasoline_store_detail, context, viewGroup)

}