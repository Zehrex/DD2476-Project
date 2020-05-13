4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/item/SketchwareProjectConfiguration.java
package com.sketch.code.two.item;

import com.google.gson.annotations.SerializedName;

public class SketchwareProjectConfiguration {

    @SerializedName("info_project_id")
    private int id;

    @SerializedName("resources_data")
    private SketchwareProjectResources resources;

    @SerializedName("list_file_path")
    private String listFilePath;

    @SerializedName("bak_folder_path")
    private String bakFolderPath;

    public SketchwareProjectResources getResources() {
        return resources;
    }

    public String getBakFolderPath() {
        return bakFolderPath;
    }

    public String getListFilePath() {
        return listFilePath;
    }

    public void setBakFolderPath(String bakFolderPath) {
        this.bakFolderPath = bakFolderPath;
    }

    public void setListFilePath(String listFilePath) {
        this.listFilePath = listFilePath;
    }

    public void setResources(SketchwareProjectResources resources) {
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
