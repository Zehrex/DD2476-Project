10
https://raw.githubusercontent.com/NearbyShops/Nearby-Shops-Android-app/master/app/src/main/java/org/nearbyshops/enduserappnew/Lists/ItemsByCategory/AdapterItemCatHorizontalList.java
package org.nearbyshops.enduserappnew.Lists.ItemsByCategory;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.nearbyshops.enduserappnew.Model.ItemCategory;
import org.nearbyshops.enduserappnew.ViewHolders.ViewHolderItemCategorySmall;

import java.util.List;





public class AdapterItemCatHorizontalList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private List<ItemCategory> dataset;
    private Context context;
    private Fragment fragment;



    public AdapterItemCatHorizontalList(List<ItemCategory> dataset, Context context, Fragment fragment) {
        this.dataset = dataset;
        this.context = context;
        this.fragment = fragment;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return ViewHolderItemCategorySmall.create(viewGroup,context,fragment,this);
    }






    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        if(viewHolder instanceof ViewHolderItemCategorySmall)
        {
            ((ViewHolderItemCategorySmall) viewHolder).bindItemCategory(dataset.get(i));
        }
    }



    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
