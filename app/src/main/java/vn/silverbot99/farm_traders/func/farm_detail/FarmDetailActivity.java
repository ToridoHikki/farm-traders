package vn.silverbot99.farm_traders.func.farm_detail;

import org.jetbrains.annotations.NotNull;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.farm_detail.presentation.FarmDetailView;
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel;

@BuildIntent
public class FarmDetailActivity extends MvpActivity {
    @Extra
    LocationFarmItemModel farm;
/*    String farmId;
    String farmName;
    String farmPhoto;*/
    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new FarmDetailView(this,new FarmDetailView.ViewCreator(this,null),farm/*farmName, farmId,farmPhoto*/);
    }
}
