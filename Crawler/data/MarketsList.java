10
https://raw.githubusercontent.com/NearbyShops/Nearby-Shops-Android-app/master/app/src/main/java/org/nearbyshops/enduserappnew/ViewHolders/ViewHolderMarket/Model/MarketsList.java
package org.nearbyshops.enduserappnew.ViewHolders.ViewHolderMarket.Model;


import org.nearbyshops.enduserappnew.Model.ModelMarket.Market;

import java.util.List;

public class MarketsList {


    private String listTitle;
    private List<Market> dataset;


    // constructors

    public MarketsList() {
    }

    public MarketsList(String listTitle, List<Market> dataset) {
        this.listTitle = listTitle;
        this.dataset = dataset;
    }


    // getter and setters


    public List<Market> getDataset() {
        return dataset;
    }

    public void setDataset(List<Market> dataset) {
        this.dataset = dataset;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
}
