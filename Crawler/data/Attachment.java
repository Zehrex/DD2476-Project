4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/api/item/Attachment.java
package com.sketch.code.two.api.item;

import com.google.gson.annotations.SerializedName;

public class Attachment {
    @SerializedName("id")
    private int id;

    @SerializedName("file_hash_sha512")
    private String sha512;

    @SerializedName("file_size")
    private int fileSize; // in bytes

    @SerializedName("owner_id")
    private int ownerId;

    public int getId() {
        return id;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getSha512() {
        return sha512;
    }
}
