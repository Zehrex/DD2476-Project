1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/Init.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;

import org.springframework.stereotype.Controller;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

@Controller
public class Init {

    private IssApiControllers issApiControllers;
    private DayNightApiController dayNightController;
    private SunRistSetController sunRistSetController;
    private TimeIssController timeIssController;
    private IssData issData;

    private int savings=0;

    public Init(IssApiControllers issApiControllers, DayNightApiController dayNightController,
                SunRistSetController sunRistSetController, IssData issData, TimeIssController timeIssController) {
        this.issApiControllers = issApiControllers;
        this.dayNightController = dayNightController;
        this.sunRistSetController = sunRistSetController;
        this.timeIssController = timeIssController;
        this.issData = issData;
        initData();
        initDataSavings();
    }

//    @EventListener(ApplicationReadyEvent.class)
    public String initData(){
        issApiControllers.getIssPosition();
        savings++;
        if(savings%2==0) {
            initDataSavings();
        }
        return "OK";
    }

    public void initDataSavings(){
        sunRistSetController.getSunRistSet();
        dayNightController.getDayNight();
        timeIssController.getTimeZone();
    }
}
