1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/DayNightApiController.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.com.nur.internationalspacestation.internationalspacestation.model.pojo.DayNight;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

@Controller
public class DayNightApiController {

    private IssData issData;
    private DayNight dayNight;
    private String lat;
    private String lon;

    @Autowired
    public DayNightApiController(IssData issData) {
        this.issData = issData;
    }

    public void getDayNight(){
        lat = issData.getLat();
        lon = issData.getLon();
        String url = "https://api.sunrise-sunset.org/json?lat=" + lat + "&lng=" + lon + "&date=today";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntityDayNight = new HttpEntity(dayNight);
        ResponseEntity<DayNight> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntityDayNight, DayNight.class);
        this.dayNight = exchange.getBody();
//        System.out.println(dayNight);
        issData.setSunriseUTC(dayNight.getResults().getSunrise());
        issData.setSunsetUTC(dayNight.getResults().getSunset());
    }

}
