package org.skynet.naveenchallenge2;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {

    static WebDriver mDriver;

    public static void main(String[] args) throws InterruptedException {
    mDriver = new ChromeDriver();
    mDriver.get("https://www.t-mobile.com/tablets");
    Thread.sleep(8000);
    String[] mFilter = {"Deals","New"};
    String[] mFilter2 = {"Brands","Apple"};
    selectFilter(mFilter2);

    }

    private static void selectFilter(String[] mFilter) throws InterruptedException {

    List<WebElement> mainFilter = mDriver.findElements(By.xpath("//legend[@data-testid='desktop-filter-group-name']"));
    List<WebElement> itemsUnderMainFilter = null;

    for (WebElement item : mainFilter){
            if (mFilter[0].equalsIgnoreCase(item.getText())){
                item.click();
                Thread.sleep(2000);
                itemsUnderMainFilter = item.findElements(By.xpath(".//ancestor::fieldset[1]//mat-checkbox"));
                break;
            }
    }


    for (int i = 1; i<mFilter.length;i++){

        String currentItem = mFilter[i];
        System.out.println("current item is "+currentItem);
        System.out.println("there are "+itemsUnderMainFilter.size() +" items under this category");


        if (currentItem == "all"){
            for (WebElement item : itemsUnderMainFilter){
                item.findElement(By.xpath(".//span[@class='mat-checkbox-frame']")).click();
            }
            break;
        }

        else {
            System.out.println("entered in else block   ");
            for (WebElement item : itemsUnderMainFilter){
                System.out.println("the text from checkbox is "+ item.findElement(By.xpath(".//span[@class='mat-checkbox-label']")).getText());
                System.out.println("should be compared with "+currentItem);
                if ( item.findElement(By.xpath(".//span[@class='mat-checkbox-label']")).getText().equalsIgnoreCase(currentItem)){
                    System.out.println("CLICK IT !!!!");
                    Actions actions = new Actions(mDriver);
                    actions.moveToElement(item.findElement(By.xpath(".//span[@class='mat-checkbox-frame']"))).click().build().perform();
                }
                else{
                    System.out.println("no match moving on");
                }
            }
        }



    }













    }
}
