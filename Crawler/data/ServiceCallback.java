9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/services/ServiceCallback.java
package com.example.covid19shahajjo.services;

public interface ServiceCallback<TResult> {
    public void onResult(TResult result);
    public void onFailed(Exception exception);
}
