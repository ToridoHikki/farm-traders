package vn.silverbot99.farm_traders.func.map;

import org.jetbrains.annotations.NotNull;

import be.trikke.intentbuilder.BuildIntent;
import be.trikke.intentbuilder.Extra;
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView;
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity;
import vn.silverbot99.farm_traders.func.map.presentation.MapView;
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel;

@BuildIntent
public class MapActivity extends MvpActivity {
    @Extra
    LocationFarmItemModel location;

    @NotNull
    @Override
    public AndroidMvpView createAndroidMvpView() {
        return new MapView(this,new MapView.ViewCreator(this,null),location);
    }
}
