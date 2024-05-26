package example;

import static stepDefinition.BasePage.*;
import static stepDefinition.HotelObjects.*;


public class MainHotelProgram {


    public static void mainProgram(String location, String hotelName, String roomtype, String numberofRooms, String dateCheckIn, String dateCheckOut, String adultRooms, String childRooms) throws Exception {

        driverSetup(url);

        waitForElementByID(username_input, 5);

        setText(findElementByID(username_input), user_username);
        setText(findElementByID(password_input), user_password);
        click(findElementByID(btnLogin));

        waitForElementByID(location_dd, 5);

        selectOptionByValue(findElementByID(location_dd), location);
        selectOptionByValue(findElementByID(hotels_dd), hotelName);
        selectOptionByValue(findElementByID(room_type_dd), roomtype);
        selectOptionByText(findElementByID(room_nos_dd), numberofRooms);

        setText(findElementByID(datepick_in), dateCheckIn);
        setText(findElementByID(datepick_out), dateCheckOut);

        selectOptionByText(findElementByID(adult_room_dd), adultRooms);
        selectOptionByText(findElementByID(child_room_dd), childRooms);

        click(findElementByID(btnSearch));

        waitForElementByID(hotel_name_0_val, 1);

        String HoletName = getAttributeValue(findElementByID(hotel_name_0_val));
        String HoletLocation = getAttributeValue(findElementByID(location_0_val));
        String HoletRooms = getAttributeValue(findElementByID(rooms_0_val));
        String HoletRoomType = getAttributeValue(findElementByID(room_type_0_val));
        String NoDays = getAttributeValue(findElementByID(no_days_0_val));

        verifyElementText(findElementByID(hotel_name_0_val), hotelName);
        verifyElementText(findElementByID(location_0_val), location);
        verifyElementText(findElementByID(room_type_0_val), roomtype);

        System.out.println(HoletName);
        System.out.println(HoletLocation);
        System.out.println(HoletRooms);
        System.out.println(HoletRoomType);
        System.out.println(NoDays);

        verifyTextPresent(location, false);
        verifyTextPresent(hotelName, false);
        verifyTextPresent(roomtype, false);
        verifyTextPresent(dateCheckIn, false);
        verifyTextPresent(dateCheckOut, false);

        click(findElementByID(radiobutton_0));
        click(findElementByID(btnContinue));

        waitForElementByID(hotel_name_dis_val, 1);

        System.out.println("Validating Book A Hotel Page");
        verifyTextPresent(location, false);
        verifyTextPresent(hotelName, false);
        verifyTextPresent(roomtype, false);

        closeWindow();

    }


}