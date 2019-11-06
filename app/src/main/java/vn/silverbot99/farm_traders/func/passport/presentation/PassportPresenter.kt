package vn.silverbot99.farm_traders.func.passport.presentation


import android.content.Intent
import android.icu.util.TimeUnit
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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







        /*(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        // there was an error
                        if (password.length() < 6) {
                            inputPassword.setError(getString(R.string.minimum_password));
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });*/
    }
}
