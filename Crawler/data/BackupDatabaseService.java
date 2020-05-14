14
https://raw.githubusercontent.com/BluezoneGlobal/react-native-bluetooth-scan/master/lib/android/src/main/java/com/scan/backup/BackupDatabaseService.java
package com.scan.backup;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.scan.backup.BackupUtils;
import com.scan.preference.AppPreferenceManager;

/**
 * Class thuc hien viec backup
 */
public class BackupDatabaseService extends IntentService {

    public BackupDatabaseService() {
        super("BackupDatabaseService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Thuc hien viec backup
        BackupUtils.backupDbToExternalStorage(getApplicationContext());

        // Set time
        AppPreferenceManager.getInstance(getApplicationContext()).setLastBackup(System.currentTimeMillis());
    }
}
