4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/item/Project.java
package com.sketch.code.two.api.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Project {

    @SerializedName("id")
    private int id;

    @SerializedName("project_name")
    private String projectName;

    @SerializedName("project_about")
    private String projectAbout;

    @SerializedName("project_icon_id")
    private int projectIcon;

    @SerializedName("project_attachment_id")
    private int project;

    @SerializedName("project_category_id")
    private int projectCategory;

    @SerializedName("project_category")
    private String projectCategoryName;

    @SerializedName("project_screenshots_ids")
    private ArrayList<Integer> projectScreenshots;

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getProjectScreenshots() {
        return projectScreenshots;
    }

    public int getProject() {
        return project;
    }

    public int getProjectCategory() {
        return projectCategory;
    }

    public int getProjectIcon() {
        return projectIcon;
    }

    public String getProjectAbout() {
        return projectAbout;
    }

    public void setProjectAbout(String projectAbout) {
        this.projectAbout = projectAbout;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectScreenshots(ArrayList<Integer> projectScreenshots) {
        this.projectScreenshots = projectScreenshots;
    }

    public void setProjectCategory(int projectCategory) {
        this.projectCategory = projectCategory;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public void setProjectIcon(int projectIcon) {
        this.projectIcon = projectIcon;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
