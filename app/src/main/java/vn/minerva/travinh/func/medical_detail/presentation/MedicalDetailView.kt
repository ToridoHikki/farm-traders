package vn.minerva.travinh.func.medical_detail.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_gasoline_store_detail.view.*
import kotlinx.android.synthetic.main.layout_medical_detail.view.*
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import vn.minerva.travinh.func.gasoline_store_detail.presentation.model.GasolineStoreDetailImageListViewModel
import vn.minerva.travinh.func.medical_detail.presentation.contact.MedicalDetailContactView
import vn.minerva.travinh.func.medical_detail.presentation.map.MedicalDetailMapView
import vn.minerva.travinh.func.medical_detail.presentation.model.MedicalDetailImageListViewModel
import android.view.animation.Animation



class MedicalDetailView (mvpActivity: MvpActivity, viewCreator: ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), MedicalDetailContract.View {
    private var appBarExpanded :Boolean = true
    private val mPresenter = MedicalDetailPresenter(AndroidScreenNavigator(mvpActivity))
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private var listData: MutableList<ViewModel> = mutableListOf()
    private var medicalDetailContactView : MedicalDetailContactView? = null
    private var medicalDetailMapView : MedicalDetailMapView? = null

    override fun showDetailToolbar(data: MedicalDetailResponse) {
        view.tvMedicalName.text = data.medicalName
        view.tvMedicalType.text = data.medicalType
        view.collapsingMedical.title = data.medicalName
    }

    override fun showImageList(data: List<ViewModel>) {
        listData.clear()
        if (data.isNotEmpty()) {
            listData.addAll(data)
        }
        listData.forEach {
            if(it is MedicalDetailImageListViewModel){
                loadImageToViewFlipper(it.imageUrl)
            }
        }
        OnAnimationViewFlipper()
    }

    private fun loadImageToViewFlipper(image: String) {
        var imageView: ImageView = ImageView(mvpActivity)
        imageView.scaleType = ImageView.ScaleType.CENTER
        Glide.with(mvpActivity).load(image).into(imageView)

        view.vlMedicalImage.addView(imageView)
        view.vlMedicalImage.setFlipInterval(3000)
        view.vlMedicalImage.isAutoStart = true

        view.vlMedicalImage.setInAnimation(mvpActivity,android.R.anim.slide_in_left)
        view.vlMedicalImage.setInAnimation(mvpActivity,android.R.anim.slide_out_right)

    }
    private fun initAppBar() {
        view.appbarMedical.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            @SuppressLint("RestrictedApi")
            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if(Math.abs(p1) > 200){
                    appBarExpanded = false;
                    view.llmedicalName.visibility = View.GONE
                    view.faMedicalCamera.visibility = View.GONE
                    view.vlMedicalImage.stopFlipping()
                }else{
                    appBarExpanded = true;
                    view.llmedicalName.visibility = View.VISIBLE
                    view.faMedicalCamera.visibility = View.VISIBLE
                    view.vlMedicalImage.startFlipping()

                }
            }

        })
    }

    override fun initData() {
        super.initData()
        mPresenter.getMedicalInfo(id)
    }
    private fun initTabBar() {
        view.tabsMedical.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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
    override fun initCreateView() {
        val toolbar = view.toolbarMedicalDetail
        mvpActivity.setSupportActionBar(toolbar)
        mvpActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        mvpActivity.supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener { /*handleOnBackPress() */mvpActivity.onBackPressed()}
        view.faMedicalCamera.setOnClickListener{OnClickfaCameraListener()}
        view.vlMedicalImage.startFlipping()
        //OnAnimationViewFlipper()

        initAppBar()
        initTabBar()
        initContactView()
        showContactView()

        initMapView()
    }

    private fun handleOnBackPress() {
        mPresenter.gotoMedicalListActivity()
    }

    private fun initContactView() {
        medicalDetailContactView = MedicalDetailContactView(mvpActivity,
            MedicalDetailContactView.ViewCreator(mvpActivity,null),id)
    }
    private fun initMapView() {
        medicalDetailMapView = MedicalDetailMapView(mvpActivity,
            MedicalDetailMapView.ViewCreator(mvpActivity,null),id)
    }
    private fun showContactView(){
        addMainContentWithView(medicalDetailContactView)
        medicalDetailContactView?.startMvpView()
        medicalDetailContactView!!.loadData()
    }
    private fun showMapView(){
        addMainContentWithView(medicalDetailMapView)
        medicalDetailMapView?.startMvpView()
        medicalDetailMapView!!.loadData()
    }
    private fun addMainContentWithView(mvpView: AndroidMvpView?) {
        mvpView?.let {
            view.vgContentMedicalDetail.removeAllViews()
            view.vgContentMedicalDetail.addView(it?.createView())
            addLifeCycle(it)
            0
        }
    }

    private fun OnClickfaCameraListener() {
        mPresenter.gotoLibrary()
    }
    private fun OnAnimationViewFlipper(){
        view.vlMedicalImage.getInAnimation().setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                val displayedChild = view.vlMedicalImage.getDisplayedChild()
                val childCount = view.vlMedicalImage.getChildCount()
                if (displayedChild <= 1) {
                    view.vlMedicalImage.stopFlipping()
                }
            }
            override fun onAnimationRepeat(animation: Animation) { }
            override fun onAnimationEnd(animation: Animation) {
                val displayedChild = view.vlMedicalImage.getDisplayedChild()
                val childCount = view.vlMedicalImage.getChildCount()
                if (displayedChild == childCount - 1) {
                    view.vlMedicalImage.stopFlipping()
                }
            }
        })
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

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_medical_detail, context, viewGroup)
}