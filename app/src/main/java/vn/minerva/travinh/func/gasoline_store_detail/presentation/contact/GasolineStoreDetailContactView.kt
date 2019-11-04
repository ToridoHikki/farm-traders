package vn.minerva.travinh.func.gasoline_store_detail.presentation.contact

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.mvpactivity.showErrorAlert
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_gasoline_store_detail_contact.view.*
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse

class GasolineStoreDetailContactView (mvpActivity: MvpActivity, viewCreator: GasolineStoreDetailContactView.ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), GasolineStoreDetailContactContract.View {
    override fun showContactInfo(data: GasolineStoreDetailResponse) {
        view.tvGasDetailStoreOwner.text = data.storeOwner
        view.tvGasDetailStoreAddress.text = data.storeAddress
        view.tvGasDetailStorePhone.text = data.storeMobile
        view.tvGasDetailCompanyOwner.text = data.companyOwner
        view.tvGasDetailCompanyName.text = data.companyName
        view.tvGasDetailCompanyAddress.text = data.companyAddress
    }

    override fun loadData() {
        mPresenter.getDataGasolineStoreContact(id)
    }
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = GasolineStoreDetailContactPresenter()

    override fun initCreateView() {

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
        AndroidMvpView.LayoutViewCreator(R.layout.layout_gasoline_store_detail_contact, context, viewGroup)
    }

