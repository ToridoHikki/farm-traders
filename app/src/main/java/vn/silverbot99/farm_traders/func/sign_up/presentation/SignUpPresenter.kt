package vn.silverbot99.farm_traders.func.passport.presentation


import android.icu.util.TimeUnit
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import vn.silverbot99.farm_traders.func.sign_up.presentation.SignUpContract
import vn.silverbot99.farm_traders.func.sign_up.presentation.model.UserFirebaseModel
import vn.silverbot99.farm_traders.func.main.MainActivity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.google.android.gms.tasks.Task


class SignUpPresenter(private val screenNavigator: ScreenNavigator) : SignUpContract.Presenter() {
    override fun gotoAuthencationPhoneView(phone: String) {
        screenNavigator.gotoVerificationPhone(phone)// suwar lai
    }

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun createAccount(userFirebaseModel: UserFirebaseModel) {
        auth.createUserWithEmailAndPassword(userFirebaseModel.email, userFirebaseModel.password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val firebaseUser = this.auth.currentUser!!
                    //tạo database cho user rồi up lên drive, lưu vào configUtil

                    view?.signUpSuccess(userFirebaseModel.email.substring(10))
                } else {
                    //Registration error
                    view?.showError("Fail")
                }
            }

    }

}