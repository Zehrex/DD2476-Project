4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/activity/ProfileEditActivity.java
package com.sketch.code.two.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sketch.code.two.R;
import com.sketch.code.two.api.item.BaseResponse;
import com.sketch.code.two.api.item.User;
import com.sketch.code.two.api.item.UserResponse;
import com.sketch.code.two.api.manager.UserManager;
import com.sketch.code.two.util.SketchcodeUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText surname;

    private EditText bio;

    private UserManager userManager;

    private Button save;

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        findViews();
        initLogic();
    }

    public void findViews () {
        firstName = findViewById(R.id.first_name);
        surname = findViewById(R.id.surname);
        bio = findViewById(R.id.bio);
        save = findViewById(R.id.save);
        toolbar = findViewById(R.id.toolbar);
    }

    public void initLogic () {
        toolbar.setNavigationOnClickListener(v -> finish());
        userManager = UserManager.getInstance();
        userManager.getUserManagerApi()
                .get(new SketchcodeUtil.User(getApplicationContext()).getToken())
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if(response.code() == 200 && response.body() != null && response.body().isSuccess() && response.body().getData() != null) {
                            User user = response.body().getData();
                            firstName.setText(user.getFirstName());
                            surname.setText(user.getSurname());
                            bio.setText(user.getBio());
                        } else {
                            Toast.makeText(ProfileEditActivity.this, getString(R.string.error_occured), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(ProfileEditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        save.setOnClickListener(v -> {
            userManager.getUserManagerApi()
                    .edit(new SketchcodeUtil.User(getApplicationContext()).getToken(), firstName.getText().toString(), surname.getText().toString(), bio.getText().toString())
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if(response.code() == 200 && response.body() != null && response.body().isSuccess())
                                Toast.makeText(ProfileEditActivity.this, getString(R.string.saved), Toast.LENGTH_SHORT).show();
                            else if(response.code() == 200 && response.body() != null)
                                Toast.makeText(ProfileEditActivity.this, response.body().getErrorsString(), Toast.LENGTH_SHORT).show();
                            else {
                                Toast.makeText(ProfileEditActivity.this, getString(R.string.error_occured), Toast.LENGTH_SHORT).show();
                            }
                            System.out.println(response.code());
                            System.out.println(response.message());
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            System.out.println(t.getLocalizedMessage());
                        }
                    });
        });
    }

}
