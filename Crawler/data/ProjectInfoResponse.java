4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/item/ProjectInfoResponse.java
package com.sketch.code.two.api.item;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProjectInfoResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("errors_messages")
    private ArrayList<Error> errorsMessages;

    @SerializedName("data")
    private Project data;

    public boolean isSuccess() {
        return success;
    }

    @Nullable
    public Project getData() {
        return data;
    }

    public ArrayList<Error> getErrorsMessages() {
        return errorsMessages;
    }

    public String getErrorsString () {
        String output = "";
        for (Error error : errorsMessages) {
            output = output.concat(error.getErrorMessage()).concat("\n");
        }
        return output.substring(0, output.lastIndexOf("\n"));
    }
}
