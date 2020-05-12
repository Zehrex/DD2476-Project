1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/model/pojo/TimeISS.java

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
    "timezone",
    "timezone_offset",
    "date",
    "date_time",
    "date_time_txt",
    "date_time_wti",
    "date_time_ymd",
    "date_time_unix",
    "time_24",
    "time_12",
    "week",
    "month",
    "year",
    "year_abbr",
    "is_dst",
    "dst_savings"
})
public class TimeISS {

    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezone_offset")
    private Integer timezoneOffset;
    @JsonProperty("date")
    private String date;
    @JsonProperty("date_time")
    private String dateTime;
    @JsonProperty("date_time_txt")
    private String dateTimeTxt;
    @JsonProperty("date_time_wti")
    private String dateTimeWti;
    @JsonProperty("date_time_ymd")
    private String dateTimeYmd;
    @JsonProperty("date_time_unix")
    private Double dateTimeUnix;
    @JsonProperty("time_24")
    private String time24;
    @JsonProperty("time_12")
    private String time12;
    @JsonProperty("week")
    private String week;
    @JsonProperty("month")
    private String month;
    @JsonProperty("year")
    private String year;
    @JsonProperty("year_abbr")
    private String yearAbbr;
    @JsonProperty("is_dst")
    private Boolean isDst;
    @JsonProperty("dst_savings")
    private Integer dstSavings;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public TimeISS() {
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("timezone_offset")
    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    @JsonProperty("timezone_offset")
    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("date_time")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("date_time")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @JsonProperty("date_time_txt")
    public String getDateTimeTxt() {
        return dateTimeTxt;
    }

    @JsonProperty("date_time_txt")
    public void setDateTimeTxt(String dateTimeTxt) {
        this.dateTimeTxt = dateTimeTxt;
    }

    @JsonProperty("date_time_wti")
    public String getDateTimeWti() {
        return dateTimeWti;
    }

    @JsonProperty("date_time_wti")
    public void setDateTimeWti(String dateTimeWti) {
        this.dateTimeWti = dateTimeWti;
    }

    @JsonProperty("date_time_ymd")
    public String getDateTimeYmd() {
        return dateTimeYmd;
    }

    @JsonProperty("date_time_ymd")
    public void setDateTimeYmd(String dateTimeYmd) {
        this.dateTimeYmd = dateTimeYmd;
    }

    @JsonProperty("date_time_unix")
    public Double getDateTimeUnix() {
        return dateTimeUnix;
    }

    @JsonProperty("date_time_unix")
    public void setDateTimeUnix(Double dateTimeUnix) {
        this.dateTimeUnix = dateTimeUnix;
    }

    @JsonProperty("time_24")
    public String getTime24() {
        return time24;
    }

    @JsonProperty("time_24")
    public void setTime24(String time24) {
        this.time24 = time24;
    }

    @JsonProperty("time_12")
    public String getTime12() {
        return time12;
    }

    @JsonProperty("time_12")
    public void setTime12(String time12) {
        this.time12 = time12;
    }

    @JsonProperty("week")
    public String getWeek() {
        return week;
    }

    @JsonProperty("week")
    public void setWeek(String week) {
        this.week = week;
    }

    @JsonProperty("month")
    public String getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(String month) {
        this.month = month;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("year_abbr")
    public String getYearAbbr() {
        return yearAbbr;
    }

    @JsonProperty("year_abbr")
    public void setYearAbbr(String yearAbbr) {
        this.yearAbbr = yearAbbr;
    }

    @JsonProperty("is_dst")
    public Boolean getIsDst() {
        return isDst;
    }

    @JsonProperty("is_dst")
    public void setIsDst(Boolean isDst) {
        this.isDst = isDst;
    }

    @JsonProperty("dst_savings")
    public Integer getDstSavings() {
        return dstSavings;
    }

    @JsonProperty("dst_savings")
    public void setDstSavings(Integer dstSavings) {
        this.dstSavings = dstSavings;
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
        return "TimeISS{" +
                "timezone='" + timezone + '\'' +
                ", timezoneOffset=" + timezoneOffset +
                ", date='" + date + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", dateTimeTxt='" + dateTimeTxt + '\'' +
                ", dateTimeWti='" + dateTimeWti + '\'' +
                ", dateTimeYmd='" + dateTimeYmd + '\'' +
                ", dateTimeUnix=" + dateTimeUnix +
                ", time24='" + time24 + '\'' +
                ", time12='" + time12 + '\'' +
                ", week='" + week + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", yearAbbr='" + yearAbbr + '\'' +
                ", isDst=" + isDst +
                ", dstSavings=" + dstSavings +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
