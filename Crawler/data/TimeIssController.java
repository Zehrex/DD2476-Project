1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/TimeIssController.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.com.nur.internationalspacestation.internationalspacestation.model.pojo.TimeISS;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TimeIssController {

    @Value("${apikeyipgeolocation}")
    private String apikeyipgeolocation;

    private TimeISS timeISS;
    private IssData issData;

    private String lat;
    private String lon;

    public TimeIssController(IssData issData) {
        this.issData = issData;
        timeISS = new TimeISS();
    }

    public void getTimeZone(){
        lat = issData.getLat();
        lon = issData.getLon();
        String url = "https://api.ipgeolocation.io/timezone?apiKey="+ apikeyipgeolocation + "&lat="+lat+"&long=" + lon;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntityTimeISS = new HttpEntity(timeISS);
        ResponseEntity<TimeISS> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntityTimeISS, TimeISS.class);
        this.timeISS = exchange.getBody();
        issData.setLocalTimeISS(LocalTime.parse(timeISS.getTime24(), DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
