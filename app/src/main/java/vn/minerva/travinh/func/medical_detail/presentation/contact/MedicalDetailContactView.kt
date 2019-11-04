package vn.minerva.travinh.func.medical_detail.presentation.contact

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_medical_detail_contact.view.*
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse

class MedicalDetailContactView (mvpActivity: MvpActivity, viewCreator: ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), MedicalDetailContactContract.View {
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = MedicalDetailContactPresenter()
    override fun loadData() {
        mPresenter.getDataMedicalContact(id)
    }

    override fun showContactInfo(data: MedicalDetailResponse) {
        view.tvMedicalDetailOwner.text = data.medicalOwner
        view.tvMedicalDetailAddress.text = data.medicalAddress
        view.tvMedicalDetailPhone.text = data.medicalMoblie
        view.tvMedicalDetailDegree.text = data.medicalQualification
        Log.d("data","showContactInfo ${data.medicalAddress}")

    }

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
    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_medical_detail_contact, context, viewGroup)
}
