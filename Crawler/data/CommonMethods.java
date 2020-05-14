15
https://raw.githubusercontent.com/Skarlet03/office_hour_cucumber_project/master/src/main/java/automationPractice/utilities/CommonMethods.java
package automationPractice.utilities;

import java.util.Map;

public interface CommonMethods {

    public default void clickButton(String button){}

    public default void enterValue(String field, String value){}

    public default void validateResult (String object, String expected){}

    public default void selectValue (String listName, String value){}

    public default void enterValue(Map<String, String> datamap){}

}
