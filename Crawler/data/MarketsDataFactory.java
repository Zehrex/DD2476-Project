10
https://raw.githubusercontent.com/NearbyShops/Nearby-Shops-Android-app/master/app/src/main/java/org/nearbyshops/enduserappnew/aSDSAdminModule/MarketsList/ViewModel/MarketsDataFactory.java
package org.nearbyshops.enduserappnew.aSDSAdminModule.MarketsList.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MarketsDataFactory extends DataSource.Factory {


    private MutableLiveData<MarketsDataSource> sourceLiveData =
            new MutableLiveData<>();

    private MarketsDataSource latestSource;




    @NonNull
    @Override
    public DataSource create() {

        latestSource = new MarketsDataSource();
        sourceLiveData.postValue(latestSource);
        return latestSource;
    }



    public MutableLiveData<MarketsDataSource> getSourceLiveData() {
        return sourceLiveData;
    }



}
