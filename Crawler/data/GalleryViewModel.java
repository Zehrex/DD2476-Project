1
https://raw.githubusercontent.com/dahilu/BeautyOverview/master/app/src/main/java/com/example/beautyoverview/ui/gallery/GalleryViewModel.java
package com.example.beautyoverview.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}