4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/item/SketchwareProjectResources.java
package com.sketch.code.two.item;

import com.google.gson.annotations.SerializedName;

public class SketchwareProjectResources {

    public SketchwareProjectResources (String iconsPath, String imagesPath, String fontsPath, String soundsPath) {
        this.iconsPath = iconsPath;
        this.imagesPath = imagesPath;
        this.fontsPath = fontsPath;
        this.soundsPath = soundsPath;
    }

    @SerializedName("icons_folder_path")
    private String iconsPath;

    @SerializedName("images_folder_path")
    private String imagesPath;

    @SerializedName("fonts_folder_path")
    private String fontsPath;

    @SerializedName("sounds_folder_path")
    private String soundsPath;

    public String getFontsPath() {
        return fontsPath;
    }

    public String getIconsPath() {
        return iconsPath;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public String getSoundsPath() {
        return soundsPath;
    }

    public void setFontsPath(String fontsPath) {
        this.fontsPath = fontsPath;
    }

    public void setIconsPath(String iconsPath) {
        this.iconsPath = iconsPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public void setSoundsPath(String soundsPath) {
        this.soundsPath = soundsPath;
    }
}
