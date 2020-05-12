1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/IssApiControllers.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.com.nur.internationalspacestation.internationalspacestation.model.pojo.DayNight;
import pl.com.nur.internationalspacestation.internationalspacestation.model.pojo.ISS;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

@Controller
public class IssApiControllers {

    private ISS iss;
    private DayNight dayNight;
    private IssData issData;

    @Autowired
    public IssApiControllers(IssData issData) {
        iss = new ISS();
        dayNight = new DayNight();
        this.issData = issData;
    }


    public void getIssPosition() {
        String url = "http://api.open-notify.org/iss-now.json";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntityISS = new HttpEntity(iss);
        ResponseEntity<ISS> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntityISS, ISS.class);
        this.iss = exchange.getBody();
        issData.setLat(iss.getIssPosition().getLatitude());
        issData.setLon(iss.getIssPosition().getLongitude());
    }


}
