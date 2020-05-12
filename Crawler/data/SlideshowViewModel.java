1
https://raw.githubusercontent.com/dahilu/BeautyOverview/master/app/src/main/java/com/example/beautyoverview/ui/slideshow/SlideshowViewModel.java
package com.example.beautyoverview.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}