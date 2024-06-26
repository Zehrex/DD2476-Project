9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/utils/GeoAddress.java
package com.example.covid19shahajjo.utils;

import android.content.Context;
import android.location.Geocoder;

import com.example.covid19shahajjo.models.GeoLocation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoAddress {

    private String subDistrict;
    private String district;
    private String division;

    private GeoLocation Location;
    private Geocoder geocoder;

    public GeoAddress(Context context, GeoLocation location){
        Location = location;
        geocoder = new Geocoder(context, Locale.ENGLISH);
        mapAddress();
    }

    private void mapAddress(){
        try {
            List<android.location.Address> addresses = geocoder.getFromLocation(Location.latitude, Location.longitude, 1);
            subDistrict = addresses.get(0).getLocality();
            district = addresses.get(0).getSubAdminArea();
            division = addresses.get(0).getAdminArea();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public String getDivision() {
        return division;
    }

    public GeoLocation getLocation() {
        return Location;
    }
}
