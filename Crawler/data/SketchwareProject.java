4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/item/SketchwareProject.java
package com.sketch.code.two.item;

import com.google.gson.annotations.SerializedName;

public class SketchwareProject {

    @SerializedName("id")
    private int id;

    @SerializedName("project_name")
    private String projectName;
    @SerializedName("project_package")
    private String projectPackage;

    @SerializedName("icon_path")
    private String iconPath;

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectPackage() {
        return projectPackage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

}
