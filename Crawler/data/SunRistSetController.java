1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/SunRistSetController.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.com.nur.internationalspacestation.internationalspacestation.model.pojo.SunRiseSet;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

@Controller
public class SunRistSetController {

    @Value("${usernamegeonames}")
    private String usernameKey;

    private SunRiseSet sunRiseSet;
    private String lat;
    private String lon;
    private IssData issData;

    @Autowired
    public SunRistSetController(IssData issData) {
        this.issData =  issData;
        sunRiseSet = new SunRiseSet();
    }


    public void getSunRistSet(){
        lat = issData.getLat();
        lon = issData.getLon();
        String url = "http://api.geonames.org/timezoneJSON?lat=" + lat + "&lng=" + lon + "&username=" + usernameKey;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntityDayNight = new HttpEntity(sunRiseSet);
        ResponseEntity<SunRiseSet> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntityDayNight, SunRiseSet.class);
        this.sunRiseSet = exchange.getBody();
        issData.setCountryName(sunRiseSet.getCountryName());
        issData.setDstOffset(Long.valueOf(sunRiseSet.getDstOffset()));
    }
}
