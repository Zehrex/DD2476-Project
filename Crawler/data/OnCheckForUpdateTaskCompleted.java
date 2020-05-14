9
https://raw.githubusercontent.com/idanapp/IdanPlusPlus/master/app/src/main/java/com/example/idan/plusplus/V2/Tasks/OnCheckForUpdateTaskCompleted.java
package com.example.idan.plusplus.V2.Tasks;

import java.io.File;

public interface OnCheckForUpdateTaskCompleted {
    void onCheckForUpdateTaskCompleted(int localVer, int serverVer, File file);
}

