2
https://raw.githubusercontent.com/vatanhorasan/myStudies/master/src/test/java/com/cybertek/tests/myStudies/Study2.java
package com.cybertek.tests.myStudies;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Study2 {

    WebDriver driver;

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(4000);
        //driver.quit();
    }

    @Test
    public void test1(){
        WebElement multiselect1 = driver.findElement(By.id("multiselect1"));

        Select select1 = new Select(multiselect1);

        List<WebElement> options = select1.getOptions();

        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }

    @Test
    public void test2() throws InterruptedException, AWTException {
        WebElement dropdownElement = driver.findElement(By.cssSelector(".dropbtn"));

        dropdownElement.click();

       Actions action = new Actions(driver);
       // action.click(dropdownElement).perform();

        List<WebElement> elements = driver.findElements(By.cssSelector("#myDropdown>a"));

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        /*
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }
        */

        for(int i=0; i<elements.size(); i++){
            String s = Keys.chord(Keys.CONTROL, Keys.ENTER);// control+click--->
            elements.get(i).sendKeys(s); //-->
            Thread.sleep(2000);
            dropdownElement.click();
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        }

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> ite = handles.iterator();

        while(ite.hasNext()){
            driver.switchTo().window(ite.next());
            Thread.sleep(2000);
            String title = driver.getTitle();
            if(title.contains("Facebook")){
                break;
            }
        }

        driver.findElement(By.cssSelector("#email")).sendKeys("group study success");

        }

}
