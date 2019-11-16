package vn.silverbot99.farm_traders.func.main.presentation

import android.app.Activity
import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_app_bar_menu_main.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.lifecycle.ViewResult
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.main.presentation.model.TabItem
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.main.data.ActionMenu
import vn.silverbot99.farm_traders.func.main.domain.MainConfigUseCase
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import kotlinex.view.visible
import vn.silverbot99.core.app.view.CustomViewPager
import vn.silverbot99.farm_traders.func.empty.EmptyFragment
import vn.silverbot99.farm_traders.func.nearest_farm.LocationFarmFragment
import vn.silverbot99.farm_traders.func.category.CategoryFragment


abstract class MainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator), MainContract.MainView {

    protected val androidScreenNavigator = AndroidScreenNavigator(mvpActivity)

    protected val mainPresenter = MainPresenter(androidScreenNavigator)
//    private var listMenuView: ListMenuView? = null
    private var emptyView: EmptyView? = null

    protected val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    protected var actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    private val resourceProvider = MainResourceProvider()
    private lateinit var adapter: Adapter
    private lateinit var viewPager: CustomViewPager
    private val notificationResultCode = 111



    companion object {
        const val HOME_FRAGMENT_ID = "home-id"
        const val LOCATION_FARM_FRAGMENT_ID = "location-farm-id"
        const val LIST_PRODUCT_FRAGMENT_ID = "product-id"
        private lateinit var mTabItems: MutableList<TabItem>
        //private var mTabItems: Int = 0
    }

    override fun initCreateView() {
        //initMenuView()
        //initHeaderView()
        initLayoutView()
        initEmptyView()
        initView()


//        initFragment()
        //initMedicalView()
    }
    private fun initView() {
        viewPager = view.viewPager
        val tabLayout: TabLayout = view.tlTabs
        setUpTabLayout(tabLayout)
        adapter = Adapter(mvpActivity.supportFragmentManager)
        mTabItems =  createTable()
        viewPager.enabledTouch = false
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        /*tabLayout.post(object: Runnable{
            override fun run() {
                tabLayout.setupWithViewPager(viewPager)
            }
        })*/

        for (i in 0 until tabLayout.tabCount) {
            val tab:TabLayout.Tab? = tabLayout.getTabAt(i)
            var resource:Int = 0
            resource = mTabItems[i].mDrawableId
            if (resource > 0) tab?.setIcon(resource)
            tab?.text = mTabItems[i].mName
        }
        viewPager.addOnPageChangeListener(onPageChangeListener)
    }

    private fun setUpTabLayout(tabLayout: TabLayout) {
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
    }

    internal class Adapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private var mAvailableFragments: SparseArray<Fragment> = SparseArray()
        override fun getItem(position: Int): Fragment {
            var fragment: MvpFragment = EmptyFragment()
            when (position) {
                0 -> fragment = EmptyFragment()
                1 -> fragment = LocationFarmFragment()
                2 -> fragment = CategoryFragment()
            }
            return fragment
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            mAvailableFragments.put(position, fragment as Fragment)
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            super.destroyItem(container, position, any)
            mAvailableFragments.remove(position)
        }

        fun getActiveFragment(position: Int): Fragment = mAvailableFragments[position]

        override fun getCount(): Int = mTabItems.size
    }

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(position: Int) {
        }

        override fun onPageScrolled(position: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
           // view.vgContent.gone()
            view.viewPager.visible()
            when (position) {
                0 -> {
                    view.tvTitleScreen.text = resourceProvider.getHomeTitle()
                }
                1 -> {
                    view.tvTitleScreen.text = resourceProvider.getFarmNearestTitle()
                }
                2 -> {
                    view.tvTitleScreen.text = resourceProvider.getListProducts()

                }
            }
        }
    }
    private fun createTable(): MutableList<TabItem> {
        val items: MutableList<TabItem> = mutableListOf()
        items.add(TabItem(HOME_FRAGMENT_ID, resourceProvider.getHomeTitle(), R.drawable.ic_home_change_value_product))
        items.add(TabItem(LOCATION_FARM_FRAGMENT_ID, resourceProvider.getFarmNearestTitle(), R.drawable.ic_nearst_farm))
        items.add(TabItem(LIST_PRODUCT_FRAGMENT_ID, resourceProvider.getListProducts(), R.drawable.ic_list_product))
        return items
    }

    override fun onViewResult(viewResult: ViewResult) {
        super.onViewResult(viewResult)
        val fragment = adapter.getActiveFragment(viewPager.currentItem)
        fragment.onActivityResult(viewResult.requestCode, viewResult.resultCode, viewResult.data)
        if (viewResult.requestCode == notificationResultCode){
            if(viewResult.resultCode == Activity.RESULT_OK){
                mainPresenter.loadConfigData()
            }
        }
    }
    abstract fun initLayoutView()
    abstract fun showHomeView()
   // abstract fun handlerClickMenu(action: ActionMenu): Boolean

    override fun showError(msgError: String) {
        mvpActivity.showErrorAlert(msgError)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    private fun initEmptyView() {
        emptyView = EmptyView(mvpActivity, EmptyView.ViewCreator(mvpActivity, null))
        emptyView?.let {
            addLifeCycle(it)
        }
    }

    override fun initData() {
        super.initData()
        mainPresenter.loadConfigData()
    }


    override fun handleAfterLoadingConfig(output: MainConfigUseCase.Output) {
        showHomeView()
    }

    override fun startMvpView() {
        mainPresenter.attachView(this)
        super.startMvpView()
//        listMenuView?.startMvpView()//%%%
        //lát thêm MedicalVIew vào giống vậy
    }

    override fun stopMvpView() {
        super.stopMvpView()
        mainPresenter.detachView()
    }

    private fun showProfileView() {
        mainPresenter.showProfileView()
    }

    fun addMainContentWithView(mvpView: AndroidMvpView?) {
/*        mvpView?.let {
            view.vgContent.removeAllViews()
            view.vgContent.addView(it.createView())
        }*/
    }



    override fun resetActionDefault() {
        actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) : LayoutViewCreator(R.layout.layout_app_bar_menu_main, context, viewGroup)
}