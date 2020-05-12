2
https://raw.githubusercontent.com/vatanhorasan/myStudies/master/src/test/java/com/cybertek/tests/myStudies/Omayo.java
package com.cybertek.tests.myStudies;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class Omayo {

    WebDriver driver;
    String [] arr = new String [6];


    @BeforeMethod
    public void setUpMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod () throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @Test
    public void testActions(){
        driver.get("http://omayo.blogspot.com/");

        Actions actions = new Actions(driver);
        WebElement blogsmenu = driver.findElement(By.id("blogsmenu"));
        actions.moveToElement(blogsmenu).perform();
        driver.findElement(By.xpath("//span[.='Selenium143']")).click();
        driver.navigate().back();

        WebElement multiSelectBox = driver.findElement(By.id("multiselect1"));
        Select multiSelect = new Select(multiSelectBox);

        List<WebElement> options = multiSelect.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }

        WebElement drop1CheckBox = driver.findElement(By.id("drop1"));
        Select drop1Select = new Select(drop1CheckBox);
        drop1Select.selectByVisibleText("doc 2");

        String displayedText = drop1Select.getFirstSelectedOption().getText();

        Assert.assertEquals(displayedText, "doc 2");
    }

    @Test
    public void testDropBox(){

        driver.get("http://omayo.blogspot.com/");
        WebElement multiSelectBox = driver.findElement(By.id("multiselect1"));
        Select multiSelect = new Select(multiSelectBox);

        List<WebElement> options = multiSelect.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }

        WebElement drop1DropBox = driver.findElement(By.id("drop1"));
        Select drop1Select = new Select(drop1DropBox);
        drop1Select.selectByVisibleText("doc 2");

        String displayedText = drop1Select.getFirstSelectedOption().getText();

        Assert.assertEquals(displayedText, "doc 2");
    }

    @Test
    public void testJSE(){
        driver.get("http://omayo.blogspot.com/");
        //driver.manage().window().setPosition(new Point(0,20));
        Actions actions = new Actions(driver);

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0, 250);");

        driver.findElement(By.id("ta1")).sendKeys("Hey here it is the text");

        jse.executeScript("window.scrollBy(0, 250);");

        WebElement textBox2 = driver.findElement(By.xpath("(//textarea)[2]"));
        textBox2.clear();
        textBox2.sendKeys("My name is Simon");

        WebElement disabledBox = driver.findElement(By.id("tb2"));
        String str = " typing";
        jse.executeScript("arguments[0].setAttribute('value', '" + str +"')",disabledBox);

    }

    @Test
    public void testFrame(){
        driver.get("http://omayo.blogspot.com/");
        driver.switchTo().frame("iframe1");
        String hotels = driver.findElement(By.linkText("Hotels")).getText();
        System.out.println("hotels = " + hotels);
        driver.switchTo().parentFrame();
        driver.switchTo().frame(2);
        driver.findElement(By.id("q")).sendKeys("hey");
        List<WebElement> chapters = driver.findElements(By.partialLinkText("Chapter"));
        for (WebElement chapter : chapters) {
            System.out.println(chapter.getText());

        }
    }

    @Test
    public void testAlerts() throws InterruptedException {
        driver.get("http://omayo.blogspot.com/");


        driver.findElement(By.id("alert1")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();


        driver.findElement(By.id("prompt")).click();

        Thread.sleep(2000);
        alert.sendKeys("name");

        Thread.sleep(2000);
        alert.accept();

        driver.findElement(By.id("confirm")).click();
        alert.accept();
    }

    @Test
    public void testCheckBox(){
        driver.get("http://omayo.blogspot.com/");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0, 250);");

        WebElement orange = driver.findElement(By.id("checkbox1"));
        WebElement blue = driver.findElement(By.id("checkbox2"));
        Assert.assertTrue(orange.isSelected());
        Assert.assertFalse(blue.isSelected());

        orange.click();
        blue.click();

        Assert.assertFalse(orange.isSelected());
        Assert.assertTrue(blue.isSelected());

    }

    @Test
    public void testTest(){
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.name("textboxn")).sendKeys("Hi i am writing");
        driver.findElement(By.className("classone")).sendKeys("Hi i am writing");
        driver.findElement(By.xpath("(//input[@class='classone'])[2]")).sendKeys("say my name");
    }

    @Test
    public void testRadio(){
        driver.get("http://omayo.blogspot.com/");
        List<WebElement> vehicles = driver.findElements(By.name("vehicle"));

        for (WebElement vehicle : vehicles) {
            vehicle.click();
            Assert.assertTrue(vehicle.isSelected());
        }
    }

    @Test
    public void testActions2() throws InterruptedException {
        driver.get("http://omayo.blogspot.com/");
        WebElement doubleclickTarget = driver.findElement(By.xpath("//button[.=' Double click Here   ']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Thread.sleep(500);
        jse.executeScript("window.scrollBy(0, 750);");
        Thread.sleep(500);
        jse.executeScript("window.scrollBy(0, 950);");

        Actions action = new Actions(driver);
        action.doubleClick(doubleclickTarget).perform();
        Alert alert = driver.switchTo().alert();
        Thread.sleep(1000);
        alert.accept();

    }

    @Test
    public void testWait() throws InterruptedException {
        driver.get("http://omayo.blogspot.com/");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.className("dropbtn")).click();
        //WebElement facebook = driver.findElement(By.cssSelector(".dropbtn~div>a:nth-of-type(1)"));
        List<WebElement> elements = driver.findElements(By.cssSelector(".dropbtn~div>a"));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        System.out.println(elements.size());

        for (WebElement element : elements) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void testWait2(){
        driver.get("http://omayo.blogspot.com/");

        driver.findElement(By.xpath("//button[.='Check this']")).click();
        WebDriverWait wait = new WebDriverWait(driver,15);
        WebElement dte = driver.findElement(By.id("dte"));
        wait.until(ExpectedConditions.elementToBeClickable(dte));
        dte.click();
        Assert.assertTrue(dte.isSelected());
    }

    @Test
    public  void testLists(){
        driver.get("http://omayo.blogspot.com/");

        List<WebElement> elements = driver.findElements(By.cssSelector("#HTML25>div>ol>li"));
        for (int i = 0; i < arr.length; i++) {
             arr[i]= elements.get(i).getText();
        }
        System.out.println(Arrays.toString(arr));

        List<WebElement> elements2 = driver.findElements(By.cssSelector("#HTML26>div>ul>li"));

        for (int i = 0; i < arr.length; i++) {
            arr[i]= elements2.get(i).getText();
        }

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void testPupUpWindow(){
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.partialLinkText("popup w")).click();
        String currentHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if(!handle.equals(currentHandle)){
                driver.switchTo().window(handle);
            }
        }

        System.out.println(driver.findElement(By.id("para1")).getText());
        System.out.println(driver.findElement(By.id("para2")).getText());

    }








}
