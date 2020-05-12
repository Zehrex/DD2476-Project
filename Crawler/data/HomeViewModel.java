1
https://raw.githubusercontent.com/dahilu/BeautyOverview/master/app/src/main/java/com/example/beautyoverview/ui/home/HomeViewModel.java
package com.example.beautyoverview.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}