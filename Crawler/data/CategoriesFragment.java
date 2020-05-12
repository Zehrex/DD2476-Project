4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/fragment/CategoriesFragment.java
package com.sketch.code.two.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sketch.code.two.R;
import com.sketch.code.two.activity.MainActivity;
import com.sketch.code.two.adapter.recyclerview.CategoriesAdapter;
import com.sketch.code.two.api.item.Category;
import com.sketch.code.two.api.manager.ProjectsManager;
import com.sketch.code.two.util.ThreadLoader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {

    private View view;

    public RecyclerView recyclerView;

    public CategoriesAdapter adapter;

    private ProjectsManager projectsManager;
    private ArrayList<Category> categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_categories, null);
        findViews();
        initLogic();
        return view;
    }

    public void findViews () {
        recyclerView = view.findViewById(R.id.categories);
    }

    public void initLogic () {
        projectsManager = ProjectsManager.getInstance();
        initList();
    }

    public void initList () {
        MainActivity.frameLayout.setVisibility(View.GONE);
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        projectsManager.getApi()
                .getCategories()
                .enqueue(new Callback<ArrayList<Category>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                        if(response.code() == 200 && response.body() != null) {
                            categories = response.body();
                            new ThreadLoader(() -> {
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                adapter = new CategoriesAdapter(categories);
                            }, () -> {
                                recyclerView.setAdapter(adapter);
                                MainActivity.frameLayout.setVisibility(View.VISIBLE);
                                MainActivity.progressBar.setVisibility(View.GONE);
                            });
                        } else {
                            initList();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                        initList();
                    }
                });
    }

}
