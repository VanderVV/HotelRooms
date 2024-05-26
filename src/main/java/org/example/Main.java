package org.example;

import static stepDefinition.BasePage.*;
import static stepDefinition.HotelObjects.*;

public class Main {


    public static void main(String[] args) {

        driverSetup(url);

        waitForElementByID(username_input, 5);

        setText(findElementByID(username_input), "AutotestB");
        setText(findElementByID(password_input), "W858OZ");
        click(findElementByID(btnLogin));

        waitForElementByID(location_dd, 5);

        selectOptionByValue(findElementByID(location_dd), "Sydney");
        selectOptionByValue(findElementByID(hotels_dd), "Hotel Sunshine");
        selectOptionByValue(findElementByID(room_type_dd), "Standard");
        selectOptionByValue(findElementByID(room_nos_dd), "2");

        setText(findElementByID(datepick_in), "25/05/2024");
        setText(findElementByID(datepick_out), "27/05/2024");

        selectOptionByValue(findElementByID(adult_room_dd), "1");
        selectOptionByValue(findElementByID(child_room_dd), "2");

        click(findElementByID(btnSearch));

        waitForElementByID(hotel_name_0_val, 1);

        String HoletName = getAttributeValue(findElementByID(hotel_name_0_val), "value");
        String HoletLocation = getAttributeValue(findElementByID(location_0_val), "value");
        String HoletRooms = getAttributeValue(findElementByID(rooms_0_val), "value");
        String HoletRoomType = getAttributeValue(findElementByID(room_type_0_val), "value");
        String NoDays = getAttributeValue(findElementByID(no_days_0_val), "value");

        System.out.println(HoletName);
        System.out.println(HoletLocation);
        System.out.println(HoletRooms);
        System.out.println(HoletRoomType);
        System.out.println(NoDays);

        verifyTextPresent("Sydney", false);
        verifyTextPresent("Standard", false);
        verifyTextPresent("Hotel Sunshine", false);
        verifyTextPresent("25/05/2024", false);

        click(findElementByID(radiobutton_0));
        click(findElementByID(btnContinue));

        waitForElementByID(hotel_name_dis_val, 1);

        System.out.println("Validating Book A Hotel Page");
        verifyTextPresent("Hotel Sunshine", false);
        verifyTextPresent("Sydney", false);
        verifyTextPresent("Standard", false);

        closeWindow();

    }


}