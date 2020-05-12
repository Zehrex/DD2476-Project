4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/item/UserResponse.java
package com.sketch.code.two.api.item;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("errors_messages")
    private ArrayList<Error> errors_messages;

    @Nullable
    @SerializedName("data")
    private User data;

    public boolean isSuccess() {
        return success;
    }

    @Nullable
    public User getData() {
        return data;
    }

    public ArrayList<Error> getErrorsMessages() {
        return errors_messages;
    }

    public String getErrorsString () {
        String output = "";
        for (Error error : errors_messages) {
            output = output.concat(error.getErrorMessage()).concat("\n");
        }
        return output.substring(0, output.lastIndexOf("\n"));
    }

}
