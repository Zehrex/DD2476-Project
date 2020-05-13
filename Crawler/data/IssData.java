1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/service/IssData.java
package pl.com.nur.internationalspacestation.internationalspacestation.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class IssData {

    private String lat;
    private String lon;
    private String countryName;
    private Long dstOffset;
    private LocalTime sunriseUTC;
    private LocalTime sunsetUTC;
    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime localTimeISS;

    private final static String EX_TERYTORY = "tereny Eksterytorialne";

    public IssData(){
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        if(countryName == null){
            this.countryName = EX_TERYTORY;
        }
        else
        this.countryName = countryName;
    }

    public Long getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(Long dstOffset) {
        if(dstOffset == 0 && countryName.equals(EX_TERYTORY)){
            this.dstOffset = Long.valueOf((long) (Double.valueOf(lon) / 15));
        }
        else
            this.dstOffset = dstOffset;
    }

    public LocalTime getSunriseUTC() {
        return sunriseUTC;
    }

    public void setSunriseUTC(LocalTime sunriseUTC) {
        this.sunriseUTC = sunriseUTC;
    }

    public void setSunriseUTC(String sunrise) {
        this.sunriseUTC = LocalTime.parse(sunrise, DateTimeFormatter.ofPattern("h:mm:ss a"));
        setSunrise(sunriseUTC);
    }

    public LocalTime getSunsetUTC() {
        return sunsetUTC;
    }

    public void setSunsetUTC(LocalTime sunsetUTC) {
        this.sunsetUTC = sunsetUTC;
    }

    public void setSunsetUTC(String sunset) {
        this.sunsetUTC = LocalTime.parse(sunset, DateTimeFormatter.ofPattern("h:mm:ss a"));
        setSunset(sunsetUTC);
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise.plusHours(getDstOffset());
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset.plusHours(getDstOffset());
    }

    public LocalTime getLocalTimeISS() {
        return localTimeISS;
    }

    public void setLocalTimeISS(LocalTime localTimeISS) {
        this.localTimeISS = localTimeISS;
    }


    @Override
    public String toString() {
        return "IssData{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", countryName='" + countryName + '\'' +
                ", dstOffset=" + dstOffset +
                ", sunriseUTC=" + sunriseUTC +
                ", sunsetUTC=" + sunsetUTC +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", localTimeISS=" + localTimeISS +
                '}';
    }
}
