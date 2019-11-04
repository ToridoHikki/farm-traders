package vn.minerva.travinh.func.medical_detail;

import android.os.Bundle;
import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView;
import vn.minerva.core.base.presentation.mvp.android.MvpActivity;
import vn.minerva.travinh.func.medical_detail.presentation.MedicalDetailView;


@BuildIntent
public class MedicalDetailActivity extends MvpActivity {
    @Extra
    int id;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new MedicalDetailView(this, new MedicalDetailView.ViewCreator(this,null),id);
    }

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }*/
}
