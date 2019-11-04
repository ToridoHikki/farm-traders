package vn.silverbot99.farm_traders.func.passport.presentation


import android.icu.util.TimeUnit
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel

class PassportPresenter(private val screenNavigator: ScreenNavigator) : PassportContract.Presenter() {
    override fun gotoLogUpView() {

    }


    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

    override fun login(userItemModel: UserItemModel) {
        view?.showLoading()

        /*var reference = FirebaseDatabase.getInstance().getReference("login")
        reference.child("user/" + userItemModel.phone).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    //user exists
                    if(dataSnapshot.getValue().toString().matches(userItemModel.password)){
                        view?.loginSuccessful()
                    } //login successful

                }else
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(userItemModel.phone,60, TimeUnit.SECONDS,this,mCallbacks);
            }

        })*/
    }
}
/*override fun login(userItemModel: UserItemModel) {
        view?.showLoading()
        loginUseCaseTask?.cancel()
        loginUseCaseTask = loginUseCase.executeAsync(object : ResultListener<PassportResponse> {
            override fun done() {
            }

            override fun fail(errorCode: Int, msgError: String) {
                view?.showError(msgError)
            }

            override fun success(data: PassportResponse) {
                view?.hideLoading()
                if (data.success) {
                    view?.handleAfterLogin(data)
                } else {
                    view?.showError(data.detail.getValueOrDefaultIsEmpty())
                }
            }
        }, passportRequest)
    }*/