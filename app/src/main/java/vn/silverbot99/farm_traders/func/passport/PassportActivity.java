package vn.silverbot99.farm_traders.func.passport;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import org.jetbrains.annotations.NotNull;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.passport.presentation.PassportView;
import vn.silverbot99.farm_traders.func.passport.presentation.SignUpView;

@BuildIntent
public class PassportActivity extends MvpActivity {
    @Extra
    int forceLogin;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new PassportView(this, new PassportView.ViewCreator(this, null), forceLogin);
    }
}

