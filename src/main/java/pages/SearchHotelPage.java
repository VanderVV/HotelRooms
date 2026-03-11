package pages;

import static base.BasePage.*;
import static pages.HotelObjects.*;
import static utils.ScreenshotUtil.captureScreenshot;
import static utils.ScreenshotUtil.captureScreenshots;

public class SearchHotelPage {

    public static void selectHotelCriteria(String location, String hotelName, String roomType, String numberOfRooms) {
        waitForElementByID(location_dd, 5);

        selectOptionByValue(findElementByID(location_dd), location);
        selectOptionByValue(findElementByID(hotels_dd), hotelName);
        selectOptionByValue(findElementByID(room_type_dd), roomType);
        selectOptionByText(findElementByID(room_nos_dd), numberOfRooms);

    }

    public static void setBookingDates(String dateCheckIn, String dateCheckOut) {
        setText(findElementByID(datepick_in), dateCheckIn);
        setText(findElementByID(datepick_out), dateCheckOut);

    }

    public static void setOccupancyDetails(String adultRooms, String childRooms) {

        selectOptionByText(findElementByID(adult_room_dd), adultRooms);
        selectOptionByText(findElementByID(child_room_dd), childRooms);

    }

    public static void verifyHotelSelect(String location, String hotelName, String roomType) {

        waitForElementByID(hotel_name_0_val, 1);

        String HotelName        = getAttributeValue(findElementByID(hotel_name_0_val));
        String HotelLocation    = getAttributeValue(findElementByID(location_0_val));
        String HotelRooms       = getAttributeValue(findElementByID(rooms_0_val));
        String HotelRoomType    = getAttributeValue(findElementBy("id",room_type_0_val));
        String NoDays           = getAttributeValue(findElementBy("name",no_days_0_val));
        String pricePreNight    = getAttributeValue(findElementBy("xpath",price_per_night_0_val));
        String totalPrice       = getAttributeValue(findElementBy("xpath",total_price_0_val));
        String arrivalDate      = getAttributeValue(findElementByID(arr_date_0_val));
        String departureDate    = getAttributeValue(findElementByID(dep_date_0_val));

        verifyElementText(findElementByID(hotel_name_0_val), hotelName);
        verifyElementText(findElementByID(location_0_val), location);
        verifyElementText(findElementByID(room_type_0_val), roomType);

        //Confirming correct currency used
        verifyElementTextContains(findElementBy("xpath",price_per_night_0_val), "AUD $ ");
        verifyElementTextContains(findElementBy("xpath",total_price_0_val), "AUD $ ");

        verifyTextPresent(hotelName, true);
        verifyTextPresent(location, true);
        verifyTextPresent(roomType, true);
        captureScreenshots(driver,HotelName);

        //Confirming
        validateTextFormat(arrivalDate, dateFormatRegex);
        validateTextFormat(departureDate, dateFormatRegex);

        System.out.println(HotelName);
        System.out.println(HotelLocation);
        System.out.println(HotelRooms);
        System.out.println(HotelRoomType);
        System.out.println(NoDays);
        System.out.println(pricePreNight);
        System.out.println(totalPrice);

    }

    public static void verifyHotelBooking(String elementName, String location, String hotelName, String roomType) {

        waitForElementByID(elementName, 10);


        System.out.println("Validating Book A Hotel Page");
        verifyTextPresent(getAttributeValue(findElementByID(elementName)), false);
        verifyTextPresent(location, false);
        verifyTextPresent(hotelName, false);
        verifyTextPresent(roomType, false);

    }

    public static void verifyBookingPage(String elementName, String location, String hotelName, String roomType) {

        waitForElementByID(elementName, 10);

        String value = getAttributeValue(findElementByID(elementName));
        System.out.println("Validating Page");
        verifyTextPresent(value, true);
        verifyTextPresent(location, true);
        verifyTextPresent(hotelName, true);
        verifyTextPresent(roomType, true);

        System.out.println("Text found for element : "+elementName +" attributeValue is :" + value);
        captureScreenshots(driver,elementName);





    }






}