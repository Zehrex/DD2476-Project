1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/main/java/pl/com/nur/internationalspacestation/internationalspacestation/controller/IssPositionApiController.java
package pl.com.nur.internationalspacestation.internationalspacestation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.nur.internationalspacestation.internationalspacestation.service.IssData;

@Controller
@RequestMapping("/")
public class IssPositionApiController {

    private IssData issData;
    private Init init;

    public IssPositionApiController(IssData issData, Init init) {
        this.issData = issData;
        this.init = init;
    }

    @GetMapping
    public String getIss(Model model){

        model.addAttribute("init", init.initData());
        model.addAttribute("isspositionLat", issData.getLat());
        model.addAttribute("isspositionLon", issData.getLon());
        model.addAttribute("isspositionCountry", issData.getCountryName());
        model.addAttribute("isspositionTime", issData.getLocalTimeISS());
        model.addAttribute("isspositionSunRise", issData.getSunrise());
        model.addAttribute("isspositionSunSet", issData.getSunset());

        return "wherisiss";
    }



}
