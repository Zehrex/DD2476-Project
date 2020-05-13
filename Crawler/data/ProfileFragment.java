4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/fragment/ProfileFragment.java
package com.sketch.code.two.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.sketch.code.two.R;
import com.sketch.code.two.activity.MainActivity;
import com.sketch.code.two.activity.ProfileEditActivity;
import com.sketch.code.two.api.item.UserResponse;
import com.sketch.code.two.api.manager.UserManager;
import com.sketch.code.two.util.GlideUtil;
import com.sketch.code.two.util.SketchcodeUtil;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class ProfileFragment extends Fragment {

    public View view;

    public TextView userFullName;
    public TextView userBio;

    public MaterialButton editProfile;

    public ImageView userAvatar;

    private UserManager userManager;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_profile, null);
        findViews();
        initLogic();
        return view;
    }

    private void findViews () {
        userFullName = view.findViewById(R.id.itemUserName);
        userBio = view.findViewById(R.id.itemUserBio);
        editProfile = view.findViewById(R.id.itemProfileButtonEdit);
        userAvatar = view.findViewById(R.id.itemUserAvatar);
    }

    private void initLogic () {
        MainActivity.progressBar.setVisibility(View.VISIBLE);
        MainActivity.frameLayout.setVisibility(View.GONE);
        userManager = UserManager.getInstance();
        userManager.getUserManagerApi()
                .get(new SketchcodeUtil.User(Objects.requireNonNull(getContext())).getToken())
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                        UserResponse output = response.body();
                        if(response.isSuccessful() && output != null && output.getData() != null && output.isSuccess()) {
                            userFullName.setText(output.getData().getFirstName().concat(" ").concat(output.getData().getSurname()));
                            userBio.setText(output.getData().getBio());
                            GlideUtil.set(output.getData().getAvatar(), userAvatar, getContext());
                        } else if(output != null) {
                            Toast.makeText(getContext(), output.getErrorsString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Auth failed", Toast.LENGTH_SHORT).show();
                        }
                        MainActivity.progressBar.setVisibility(View.GONE);
                        MainActivity.frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        initLogic();
                    }
                });
        editProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfileEditActivity.class)));
    }

}
