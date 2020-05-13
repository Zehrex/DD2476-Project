1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/IPMJobsService.java
package com.agm.ipmanager;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.agm.ipmanager.credentials.CredentialsManager;

public class IPMJobsService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        this.updateIPManager(params);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

    private void updateIPManager(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (CredentialsManager.getInstance().isOnline()) {
                        IPManager.getInstance().recalculateServicesStatus();
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
