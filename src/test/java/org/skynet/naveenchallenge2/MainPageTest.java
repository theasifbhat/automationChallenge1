package org.skynet.naveenchallenge2;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.List;

public class MainPageTest {

    static WebDriver mDriver;
    static WebDriverWait wait;

    public static void main(String[] args){
    mDriver = new ChromeDriver();
    wait = new WebDriverWait(mDriver,Duration.ofSeconds(8000));
    mDriver.get("https://www.t-mobile.com/tablets");

    String[] mFilter = {"Deals","New"};
    String[] mFilter2 = {"Brands","Alcatel","Apple"};
    String[] mFilter3 = {"Operating System","all"};

    //we can select any string array from above
    selectFilter(mFilter);

    }



    private static void selectFilter(String[] mFilter){

    By mainFilterLocator = By.xpath("//legend[@data-testid='desktop-filter-group-name']");
    By allOptionsInsideMainFilterLocator= By.xpath(".//ancestor::fieldset[1]//mat-checkbox");
    By checkBoxForRelativeItemLocator = By.xpath(".//span[@class='mat-checkbox-frame']");
    By checkBoxLabelRelativeItemLocator = By.xpath(".//span[@class='mat-checkbox-label']");
    Actions actions = new Actions(mDriver);

    wait.until(ExpectedConditions.visibilityOfAllElements(mDriver.findElements(mainFilterLocator)));

    List<WebElement> mainFilter = mDriver.findElements(mainFilterLocator);
    List<WebElement> itemsUnderMainFilter = null;

    //gets the webelement for main filter
    for (WebElement item : mainFilter){
            if (mFilter[0].equalsIgnoreCase(item.getText())){
                item.click();
                wait.until(ExpectedConditions.visibilityOfAllElements(item.findElements(allOptionsInsideMainFilterLocator)));
                itemsUnderMainFilter = item.findElements(allOptionsInsideMainFilterLocator);
                break;
            }
    }
    // checks if itemsundermainfilter
    if (itemsUnderMainFilter == null){
            Assert.fail("failed to get sub categories under main category");
    }


    //check all the items under main filter and compares from the second item in a string array as first item is the name of main filter

    for (int i = 1; i<mFilter.length;i++){
        String currentItem = mFilter[i];

        // if the first item is all then mark all items in that main filter

        if(1==i && "all".equals(currentItem)) {
            for (WebElement item : itemsUnderMainFilter){
                actions.moveToElement(item.findElement(checkBoxForRelativeItemLocator)).click().build().perform();
            }
            break;
        }
        // else only those which are included from the string array's second item
        else {
            for (WebElement item : itemsUnderMainFilter){
                if ( item.findElement(checkBoxLabelRelativeItemLocator).getText().equalsIgnoreCase(currentItem)){
                    actions.moveToElement(item.findElement(checkBoxForRelativeItemLocator)).click().build().perform();
                }
            }
        }
    }
    }
}
