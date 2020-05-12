1
https://raw.githubusercontent.com/zhackerx/upi_payment/master/app/src/main/java/com/millionq/upi_payment/WalletsFragment.java
package com.millionq.upi_payment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class WalletsFragment extends Fragment {

    static final String KEY_NUMBER = "number";
    static final String KEY_CASH = "cash";

    ListView list;
    MyAdapter2 adapter;
    DBHelper mydb;

    public static WalletsFragment newInstance() {
        return new WalletsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallets, container, false);

        mydb = new DBHelper(getActivity());
        ArrayList array_list = mydb.getAllWallets();

        ArrayList<HashMap<String, String>> walletsList = new ArrayList<HashMap<String, String>>();
        for (int i=0;i<array_list.size();i+=2)
        {
            HashMap<String, String> map = new HashMap <String, String>();
            map.put(KEY_NUMBER, array_list.get(i).toString());
            map.put(KEY_CASH, array_list.get(i+1).toString());
            walletsList.add(map);
        }

        list=(ListView)v.findViewById(R.id.listView2);

        // Getting adapter by passing xml data ArrayList
        adapter=new MyAdapter2(getActivity(), walletsList);
        list.setAdapter(adapter);

        return v;
    }
}
