package vn.silverbot99.farm_traders.func.verification_phone;

import org.jetbrains.annotations.NotNull;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.verification_phone.presentation.VerificationView;

/*public class VerificationActivity: MvpActivity {
}*/
@BuildIntent
public class VerificationActivity extends MvpActivity {
    @Extra
    String phone;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
//        return new SignUpView(this, new SignUpView.ViewCreator(this, null), forceLogin);
        return new VerificationView(this, new VerificationView.ViewCreator(this, null), phone);
    }
}
