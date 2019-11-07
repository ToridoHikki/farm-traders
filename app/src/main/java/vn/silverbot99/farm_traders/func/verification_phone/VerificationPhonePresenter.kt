package vn.silverbot99.farm_traders.func.verification_phone

import android.view.View
import android.widget.ProgressBar
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.silverbot99.farm_traders.app.presentation.navigation.ScreenNavigator
import java.util.concurrent.TimeUnit
import android.widget.Toast
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import vn.silverbot99.farm_traders.app.config.ConfigUtil


class VerificationPhonePresenter(var screenNavigator: ScreenNavigator): VerificationPhoneContract.Presenter() {
    var verificationId: String =""
    var token: PhoneAuthProvider.ForceResendingToken? = null
   var mAuth:FirebaseAuth = FirebaseAuth.getInstance();
    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
//            super.onCodeSent(p0, p1)
            Log.d("Verification","verificationId: ${p0}")
            verificationId = p0.getValueOrDefaultIsEmpty()
            token = p1

        }
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential?) {
            val code = phoneAuthCredential?.getSmsCode()
            if (code != null) {
                view?.getCode(code)
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(p0: FirebaseException?) {
            view?.showError(p0?.message.getValueOrDefaultIsEmpty())
            view?.hideLoading()
        }

        override fun onCodeAutoRetrievalTimeOut(s: String) {
            super.onCodeAutoRetrievalTimeOut(s)
            verificationId = s
            view?.showLoading()
        }


    }
    override fun sendVerificationCode(phone: String) {
        view?.showLoading()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phone,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            callbacks
        );
        view?.hideLoading()
    }
    override fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if(task.isSuccessful){
                        view?.verificateSuccess()
                        ConfigUtil.savePassportUID(mAuth.currentUser?.uid)
                    }
                    else{
                        view?.showError(task.exception.toString())
                        view?.hideLoading()
                    }
                }

            })
    }


    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

}