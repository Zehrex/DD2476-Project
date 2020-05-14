10
https://raw.githubusercontent.com/NearbyShops/Nearby-Shops-Android-app/master/app/src/main/java/org/nearbyshops/enduserappnew/Lists/OrderHistoryPaging/ViewModel/ViewModelOrders.java
package org.nearbyshops.enduserappnew.Lists.OrderHistoryPaging.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelOrders extends AndroidViewModel {


    private Executor executor;
    private LiveData<PagedList<Object>> articleLiveData;

    private OrdersDataFactory feedDataFactory;




    public ViewModelOrders(@NonNull Application application) {
        super(application);

        init();
    }




    private void init() {

        executor = Executors.newFixedThreadPool(5);

        feedDataFactory = new OrdersDataFactory();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10)
                        .build();


        articleLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();

    }





    public void refresh()
    {

        feedDataFactory.getSourceLiveData().getValue().invalidate();
    }





    public LiveData<PagedList<Object>> getArticleLiveData() {
        return articleLiveData;
    }
}