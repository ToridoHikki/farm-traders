package vn.minerva.travinh.func.passport;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import org.jetbrains.annotations.NotNull;
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView;
import vn.minerva.core.base.presentation.mvp.android.MvpActivity;
import vn.minerva.travinh.func.passport.presentation.PassportView;

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

