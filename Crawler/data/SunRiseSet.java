1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/model/pojo/SunRiseSet.java
package pl.com.nur.internationalspacestation.internationalspacestation.model.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sunrise",
        "lng",
        "countryCode",
        "gmtOffset",
        "rawOffset",
        "sunset",
        "timezoneId",
        "dstOffset",
        "countryName",
        "time",
        "lat"
})
public class SunRiseSet {

    @JsonProperty("sunrise")
    private String sunrise;
    @JsonProperty("lng")
    private Double lng;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("gmtOffset")
    private Integer gmtOffset;
    @JsonProperty("rawOffset")
    private Integer rawOffset;
    @JsonProperty("sunset")
    private String sunset;
    @JsonProperty("timezoneId")
    private String timezoneId;
    @JsonProperty("dstOffset")
    private Integer dstOffset;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("time")
    private String time;
    @JsonProperty("lat")
    private Integer lat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SunRiseSet() {
    }

    @JsonProperty("sunrise")
    public String getSunrise() {
        return sunrise;
    }

    @JsonProperty("sunrise")
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    @JsonProperty("lng")
    public Double getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(Double lng) {
        this.lng = lng;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("gmtOffset")
    public Integer getGmtOffset() {
        return gmtOffset;
    }

    @JsonProperty("gmtOffset")
    public void setGmtOffset(Integer gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    @JsonProperty("rawOffset")
    public Integer getRawOffset() {
        return rawOffset;
    }

    @JsonProperty("rawOffset")
    public void setRawOffset(Integer rawOffset) {
        this.rawOffset = rawOffset;
    }

    @JsonProperty("sunset")
    public String getSunset() {
        return sunset;
    }

    @JsonProperty("sunset")
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @JsonProperty("timezoneId")
    public String getTimezoneId() {
        return timezoneId;
    }

    @JsonProperty("timezoneId")
    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    @JsonProperty("dstOffset")
    public Integer getDstOffset() {
        return dstOffset;
    }

    @JsonProperty("dstOffset")
    public void setDstOffset(Integer dstOffset) {
        this.dstOffset = dstOffset;
    }

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("lat")
    public Integer getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Integer lat) {
        this.lat = lat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "SunRiseSet{" +
                "sunrise='" + sunrise + '\'' +
                ", lng=" + lng +
                ", countryCode='" + countryCode + '\'' +
                ", gmtOffset=" + gmtOffset +
                ", rawOffset=" + rawOffset +
                ", sunset='" + sunset + '\'' +
                ", timezoneId='" + timezoneId + '\'' +
                ", dstOffset=" + dstOffset +
                ", countryName='" + countryName + '\'' +
                ", time='" + time + '\'' +
                ", lat=" + lat +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}