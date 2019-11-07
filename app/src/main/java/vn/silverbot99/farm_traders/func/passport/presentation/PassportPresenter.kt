package vn.silverbot99.farm_traders.func.passport.presentation


import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel

class PassportPresenter(private val screenNavigator: ScreenNavigator) : PassportContract.Presenter() {
    override fun gotoLogUpView() {
        screenNavigator.gotoSignUpScreen()
    }


    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

    private var auth = FirebaseAuth.getInstance()
    override fun login(userItemModel: UserItemModel) {
        view?.showLoading()
        var email: String = "${userItemModel.phone}@gmail.com"
        auth.signInWithEmailAndPassword(email, userItemModel.password)
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if(task.isSuccessful){
                    ConfigUtil.savePassportUID(auth.currentUser?.uid)
                    gotoMainActivity()

                }else{
                    view?.showError("Error: ${task.exception?.message}")
                }
            })

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