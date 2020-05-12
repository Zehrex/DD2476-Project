4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/item/Projects.java
package com.sketch.code.two.api.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Projects {

    @SerializedName("projects_name")
    private String projectsName;

    @SerializedName("projects_desc")
    private String projectsDesc;

    @SerializedName("list")
    private ArrayList<Project> projects;

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public String getProjectsDesc() {
        return projectsDesc;
    }

    public String getProjectsName() {
        return projectsName;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setProjectsDesc(String projectsDesc) {
        this.projectsDesc = projectsDesc;
    }

    public void setProjectsName(String projectsName) {
        this.projectsName = projectsName;
    }
}
