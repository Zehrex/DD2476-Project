9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/utils/ConnectivityListener.java
package com.example.covid19shahajjo.utils;
import android.app.Application;

import com.example.covid19shahajjo.services.ConnectivityReceiver;

public class ConnectivityListener extends Application {
    private static ConnectivityListener mConnectivityListener;
    @Override
    public void onCreate() {
        super.onCreate();

        mConnectivityListener = this;
    }

    public static synchronized ConnectivityListener getInstance() {
        return mConnectivityListener;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
