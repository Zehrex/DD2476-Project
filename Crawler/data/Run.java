15
https://raw.githubusercontent.com/Skarlet03/office_hour_cucumber_project/master/src/main/java/automationPractice/runner/Run.java
package automationPractice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "automationPractice/step_definitions",
        dryRun = false,
        tags = "@TC004"
)

public class Run {



}
