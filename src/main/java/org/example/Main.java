package org.example;
import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import stepDefinition.WebDriverChrome;

import static java.sql.DriverManager.getDriver;
import static stepDefinition.BasePage.*;
import static stepDefinition.HotelObjects.*;


public class Main {

    private static ThreadLocal<WebDriver> driverFinal = new ThreadLocal<WebDriver>();

    public static void main(String[] args) {



        System.setProperty(chromeWebdriver, chromedriverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        setDriver(driver);


//        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driverFinal.set(driver);
//        openBrowser(url);
        driver.get(url);
        driver.navigate().refresh();
        maximizeWindow();

        waitForElement( username_input, 5);

        setText( findElementBy(byID, username_input, driver) , "AutotestB");
        setText( findElementBy(byID, password_input, driver) , "W858OZ");
        click( findElementBy(byID, btnLogin, driver) );

        waitForElement( location_dd, 5);

        selectOptionByValue( findElementBy(byID, location_dd, driver) , "Sydney");
        selectOptionByValue( findElementBy(byID, hotels_dd, driver) , "Hotel Sunshine");
        selectOptionByValue( findElementBy(byID, room_type_dd, driver) , "Standard");
        selectOptionByValue( findElementBy(byID, room_nos_dd, driver) , "2");

        setText( findElementBy(byID, datepick_in, driver) , "25/05/2024");
        setText( findElementBy(byID, datepick_out, driver) , "27/05/2024");

        selectOptionByValue( findElementBy(byID, adult_room_dd, driver) , "1");
        selectOptionByValue( findElementBy(byID, child_room_dd, driver) , "2");

        click( findElementBy(byID, btnSearch, driver) );

        waitForElement( hotel_name_0_val, 1);

        String HoletName        = getAttributeValue( findElementBy(byID, hotel_name_0_val, driver) , "value");
        String HoletLocation    = getAttributeValue( findElementBy(byID, location_0_val, driver), "value" );
        String HoletRooms       = getAttributeValue( findElementBy(byID, rooms_0_val, driver),"value" );
        String HoletRoomType    = getAttributeValue( findElementBy(byID, room_type_0_val, driver),"value" );
        String NoDays           = getAttributeValue( findElementBy(byID, no_days_0_val, driver),"value" );

        System.out.println(HoletName);
        System.out.println(HoletLocation);
        System.out.println(HoletRooms);
        System.out.println(HoletRoomType);
        System.out.println(NoDays);

        verifyTextPresent("Sydney", false);
        verifyTextPresent("Standard", false);
        verifyTextPresent("Hotel Sunshine", false);
        verifyTextPresent("25/05/2024", false);

        click( findElementBy(byID, radio_radiobutton_0, driver) );
        click( findElementBy(byID, btnContinue, driver) );

        waitForElement( hotel_name_dis_val, 1);

        System.out.println("Validating Book A Hotel Page");
        verifyTextPresent("Hotel Sunshine", false);
        verifyTextPresent("Sydney", false);
        verifyTextPresent("Standard", false);

        closeWindow();


    }

    public static WebElement findElementBy(String finder, String value, WebDriver driver)
    {
        WebElement element;
        try
        {
            switch (finder)
            {
                case "id":
                    element = driver.findElement(By.id(value));
                    break;
                case "name":
                    element = driver.findElement(By.name(value));
                    break;
                case "className":
                    element = driver.findElement(By.className(value));
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(value));
                    break;
                default:
                    element = driver.findElement(By.xpath(value));


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return element;

    }

    public static WebDriver getDriver()
    {
        return driverFinal.get();
    }

}