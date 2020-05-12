4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/item/BackupedProjectConfiguration.java
package com.sketch.code.two.item;

import com.google.gson.annotations.SerializedName;

public class BackupedProjectConfiguration {
    @SerializedName("sketchware_configuration")
    public SketchwareProjectConfiguration sketchwareProjectConfiguration;

    @SerializedName("base_path")
    public String basePath;

}
