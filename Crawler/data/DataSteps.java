15
https://raw.githubusercontent.com/Skarlet03/office_hour_cucumber_project/master/src/main/java/automationPractice/step_definitions/DataSteps.java
package automationPractice.step_definitions;

import automationPractice.pages.BasePage;
import automationPractice.utilities.PageObjects;
import io.cucumber.java.en.Given;

public class DataSteps {

    @Given("User gets the test data from {string} excel {string} sheet {string} row")
    public void user_gets_the_test_data_from_excel_sheet_row(String docName, String sheetName, String rowNum) {
        BasePage basePage = PageObjects.getPageObject("Base");
        basePage.getData(docName, sheetName, rowNum);
    }

}
