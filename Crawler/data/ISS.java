1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/model/pojo/ISS.java

package pl.com.nur.internationalspacestation.internationalspacestation.model.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.context.annotation.Bean;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "iss_position",
    "message",
    "timestamp"
})

public class ISS {

    @JsonProperty("iss_position")
    private IssPosition issPosition;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ISS() {
    }

    @JsonProperty("iss_position")
    public IssPosition getIssPosition() {
        return issPosition;
    }

    @JsonProperty("iss_position")
    public void setIssPosition(IssPosition issPosition) {
        this.issPosition = issPosition;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
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
        return "ISS{" +
                "issPosition=" + issPosition +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
