2
https://raw.githubusercontent.com/vatanhorasan/myStudies/master/src/test/java/com/cybertek/tests/myStudies/Study1.java
package com.cybertek.tests.myStudies;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Study1 {

    WebDriver driver;

    @BeforeMethod
    public void setUpMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void testAlert() throws InterruptedException {
        driver.findElement(By.cssSelector("button[onclick='myFunctionf()']")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Say my name");
        alert.accept();
    }

    @Test
    public void dropDown() throws InterruptedException {
        WebElement dropBox = driver.findElement(By.xpath("(//select)[2]"));

        Select selectCountries = new Select(dropBox);
        int size = selectCountries.getOptions().size();
        WebElement rightArrow = driver.findElement(By.xpath("(//td)[2]/input[1]"));
        WebElement leftArrow = driver.findElement(By.xpath("(//td)[2]/input[2]"));

        for (int i = 0; i < size; i++) {
            selectCountries.selectByIndex(0);
            rightArrow.click();
        }
        selectCountries = new Select(driver.findElement(By.xpath("(//select)[3]")));
        for (int i = 0; i < size; i++) {
            selectCountries.selectByIndex(0);
            leftArrow.click();
        }

    }

    @Test
    public void newPage(){

        String handle1 = driver.getWindowHandle();
        driver.findElement(By.linkText("Open New Page")).click();

        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            if(!handle.equals(handle1)){
                driver.switchTo().window(handle);
                break;
            }
        }

        WebElement dropBox = driver.findElement(By.xpath("(//select)[2]"));
        Select selectCountries = new Select(dropBox);
        int size = selectCountries.getOptions().size();
        WebElement rightArrow = driver.findElement(By.xpath("(//td)[2]/input[1]"));
        WebElement leftArrow = driver.findElement(By.xpath("(//td)[2]/input[2]"));

        for (int i = 0; i < size; i++) {
            selectCountries.selectByIndex(0);
            rightArrow.click();
        }
    }

    @Test
    public void tableTest(){
        WebElement table = driver.findElement(By.xpath("(//table)[2]"));
        //System.out.println(table.getText());

        int rowNum = driver.findElements(By.xpath("(//table)[2]//tr")).size();
        int colNum = driver.findElements(By.xpath("(//table)[2]//tr[1]/td")).size();

        WebElement firstCell = driver.findElement(By.xpath("(//table)[2]//tr[1]/td[1]"));

        //printing all the cells

        for (int i=1 ; i<=rowNum ; i++){
            for(int j=1 ; j<=colNum ; j++){
                if(i==3 && j==6){
                    break;
                }
                String xPath = "(//table)[2]//tr["+i+"]/td["+j+"]";
                String cellText = driver.findElement(By.xpath(xPath)).getText();
                double num = Double.parseDouble(cellText);
                if(num%2==0){
                    System.out.print(num +" | ");
                }
            }
            System.out.println();
        }



    }


}
