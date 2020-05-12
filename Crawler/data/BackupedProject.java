4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/item/BackupedProject.java
package com.sketch.code.two.item;

import com.google.gson.annotations.SerializedName;

public class BackupedProject {

    @SerializedName("sketchware_project")
    private SketchwareProject project;

    @SerializedName("time")
    private long time;

    @SerializedName("configuration")
    private SketchwareProjectConfiguration configuration = new SketchwareProjectConfiguration();

    public long getTime() {
        return time;
    }


    public SketchwareProjectConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(SketchwareProjectConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public SketchwareProject getProject() {
        return project;
    }

    public void setProject(SketchwareProject project) {
        this.project = project;
    }
}
