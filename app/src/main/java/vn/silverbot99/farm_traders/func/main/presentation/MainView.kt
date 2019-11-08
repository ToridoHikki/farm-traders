package vn.silverbot99.farm_traders.func.main.presentation

import android.content.Context
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import kotlinex.context.showAlert
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_app_bar_menu_main.view.*
import kotlinx.android.synthetic.main.layout_main.view.*
import kotlinx.android.synthetic.main.layout_nav_header_main.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.app.util.image.GlideImageHelper
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionData
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.lifecycle.ViewResult
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.main.data.ActionMenu
import vn.silverbot99.farm_traders.func.main.domain.MainConfigUseCase
//import vn.silverbot99.farm_traders.func.main.presentation.menu.ListMenuView
import vn.silverbot99.farm_traders.func.main.presentation.model.MenuViewModel
import android.R.id
import android.support.design.widget.BottomNavigationView
import android.R.id
import android.support.v4.app.Fragment
import android.view.MenuItem


abstract class MainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator), MainContract.MainView {

    protected val androidScreenNavigator = AndroidScreenNavigator(mvpActivity)

    protected val mainPresenter = MainPresenter(androidScreenNavigator)
//    private var listMenuView: ListMenuView? = null
    private var emptyView: EmptyView? = null

    protected val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    protected var actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    private val resourceProvider = MainResourceProvider()

    override fun initCreateView() {
        //initMenuView()
        //initHeaderView()
        initLayoutView()
        initEmptyView()
        initBottomNavigation()
//        initFragment()
        //initMedicalView()
    }

    private fun initBottomNavigation() {
        val navigation:BottomNavigationView = view.navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                val fragment: Fragment
                when (p0.getItemId()) {
                    R.id.navigation_shop -> {
                        toolbar.setTitle("Shop")
                        return true
                    }
                    R.id.navigation_gifts -> {
                        toolbar.setTitle("My Gifts")
                        return true
                    }
                    R.id.navigation_cart -> {
                        toolbar.setTitle("Cart")
                        return true
                    }
                    R.id.navigation_profile -> {
                        toolbar.setTitle("Profile")
                        return true
                    }
                }
                return false
            }
        }

/*
    private fun initFragment() {
        mTabItems = createTable()
        adapter = Adapter(mvpActivity.supportFragmentManager)
        val tabLayout: TabLayout = view.tlTabs
        viewPager = view.viewPager
        viewPager.enabledTouch = false
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            val resource = mTabItems[i].mDrawableId
            if (resource > 0) tab?.setIcon(resource)
            tab?.text = mTabItems[i].mName
        }
        viewPager.addOnPageChangeListener(onPageChangeListener)
        view.ivIconFirst.setImageResource(resourceProvider.getIconNotification())
        setupHomeToolbar()

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
    internal class Adapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private var mAvailableFragments: SparseArray<Fragment> = SparseArray()
        override fun getItem(position: Int): Fragment {
            var fragment: MvpFragment = EmptyFragment()
            when (position) {
                0 -> fragment = HomeFragment()
                1 -> fragment = ExploreFragment()
                2 -> fragment = ExperienceFragment()
                3 -> fragment = DIYTourFragment()
                4 -> fragment = UtilitiesFragment()
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

    private fun createTable(): MutableList<TabItem> {
        val items: MutableList<TabItem> = mutableListOf()
        items.add(TabItem(HOME_FRAGMENT_ID, "Home", R.drawable.ic_home_selector))
        items.add(TabItem(EXPLORE_FRAGMENT_ID, "Explore", R.drawable.ic_explore_selector))
        items.add(
            TabItem(
                EXPERIENCES_FRAGMENT_ID,
                "Experiences",
                R.drawable.ic_experiences_selector
            )
        )
        items.add(TabItem(DIY_TOUR_FRAGMENT_ID, "DIY Tour", R.drawable.ic_diytour_selector))
        items.add(TabItem(UTILLITIES_FRAGMENT_ID, "Utilities", R.drawable.ic_utillities_selector))
        return items
    }
*/




    /*  private fun initHeaderView() {
          val passportUid = ConfigUtil.passportUid
          passportUid?.let {
              view.text_view_nav_header_full_name.text = it.ruleName
              view.text_view_nav_header_email.text = it.fullName
              val imageHelper = GlideImageHelper(mvpActivity)
              imageHelper.loadAvatar(view.image_view_nav_header_avatar, it.avatarUrl)
          }
          view.navigation_header_container.setOnClickListener {
              runWithCheckMultiTouch("onActionOpenProfile", object : OnActionNotify {
                  override fun onActionNotify() {
                      handleOnClickItemMenu(MenuViewModel("", 0, false, false, ActionMenu.ACTION_MENU_HEADER_PROFILE))
                  }
              })
          }
      }*/

   /* private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(position: Int) {
        }

        override fun onPageScrolled(position: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
            if (isOpenMessagingView) {
                isOpenMessagingView = false
                view.viewPager.visible()
                view.flContent.gone()
            }
            when (position) {
                0 -> {
                    setupHomeToolbar()
                }
                1 -> {
                    setupExploreToolbar()
                }
                2 -> {
                    setupExperiencesToolbar()
                }
                3 -> {
                    setupDIYTourToolbar()
                }
                4 -> {
                    setupUtilitiesToolbar()
                }
            }
        }
    }*/

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
/*
    private fun initMenuView() {
        listMenuView = ListMenuView(mvpActivity, view.rvMenuItem, view.drawerLayout, view.toolbar, object :
            OnActionData<MenuViewModel> {
            override fun onAction(data: MenuViewModel) {
                closeMenu()
                handleOnClickItemMenu(data)
            }
        })
        listMenuView?.let { listMenuView ->
            listMenuView.createView()
            addLifeCycle(listMenuView)
        }
    }*/


   /* protected fun handleOnClickItemMenu(data: MenuViewModel) {
        if (actionMenuCurrent != data.action) {
            if (data.action != ActionMenu.ACTION_MENU_LOGOUT) {
                actionMenuCurrent = data.action
            }
            if (!handlerClickMenu(data.action)) {
                when (data.action) {
                    ActionMenu.ACTION_MENU_LOGOUT -> {
                        logout()
                    }
                    ActionMenu.ACTION_MENU_HEADER_PROFILE -> {
                        showProfileView()
                    }

                    else -> showEmptyView(data)
                }

            }
        }
    }



    private fun closeMenu() {
        if (view.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            view.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }*/

    override fun stopMvpView() {
        super.stopMvpView()
        mainPresenter.detachView()
    }

    private fun showEmptyView(model: MenuViewModel) {
        addMainContentWithView(emptyView)
    }

    private fun showProfileView() {
        mainPresenter.showProfileView()
    }

    fun addMainContentWithView(mvpView: AndroidMvpView?) {
        mvpView?.let {
            view.vgContent.removeAllViews()
            view.vgContent.addView(it.createView())
        }
    }


    private fun logout() {
        mvpActivity.showAlert(resourceProvider.getLogoutMessageDialog(), "", object : OnActionNotify {
            override fun onActionNotify() {
                ConfigUtil.savePassport(null)
                mainPresenter.gotoLoginActivity()
            }
        }, object : OnActionNotify {
            override fun onActionNotify() {

            }
        })
    }

    override fun resetActionDefault() {
        actionMenuCurrent = ActionMenu.ACTION_DEFAULT
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) : LayoutViewCreator(R.layout.layout_main, context, viewGroup)
}