package example;

import static base.BasePage.*;
import static pages.BookHotelPage.*;
import static pages.HotelObjects.*;
import static pages.LoginFlow.loginWithValidUser;
import static pages.SearchHotelPage.*;
import static utils.ConfigReader.get;
import static utils.ScreenshotUtil.captureScreenshot;


public class MainHotelProgram {


    public static void mainProgram(String location, String hotelName, String roomType, String numberOfRooms, String dateCheckIn, String dateCheckOut, String adultRooms, String childRooms, String firstName, String lastName, String billingAddress, String ccNo, String ccType, String ccMonth, String ccYear, String ccCvvNo) throws Exception {

        driverSetup(get("url"));

        loginWithValidUser();

        selectHotelCriteria(location, hotelName, roomType, numberOfRooms);
        setBookingDates(dateCheckIn, dateCheckOut);
        setOccupancyDetails(adultRooms, childRooms);

        click(findElementByID(btnSearch));

        verifyHotelSelect(location, hotelName, roomType);

        click(findElementByID(radiobutton_0));
        click(findElementByID(btnContinue));

        verifyBookingPage(hotel_name_dis_val,location, hotelName, roomType);
        //take page screenshot

        bookHotelCriteria(firstName, lastName, billingAddress, ccNo, ccType, ccMonth, ccYear, ccCvvNo);

        click(findElementByID(btnBook_now));

        verifyBookingPage(order_no_val,location, hotelName, firstName);
        //take page screenshot

        click(findElementByID(btnMyItinerary));

        waitForElementBy("name",btnCancelSelected, 15);

        //take page screenshot
        captureScreenshot(driver,"To Cancel Selected");


        click(findElementByID(btnLogout));

        verifyTextPresent(logoutSuccess, false);

        captureScreenshot(driver,"Logout Success");




        //take page screenshot




















       // closeWindow();

    }

    public static void mainProgramTwo(String location, String hotelName, String roomType, String numberOfRooms, String dateCheckIn, String dateCheckOut, String adultRooms, String childRooms, String firstName, String lastName, String billingAddress, String ccNo, String ccType, String ccMonth, String ccYear, String ccCvvNo) throws Exception {

        driverSetup(get("url"));

        loginWithValidUser();

        selectHotelCriteria(location, hotelName, roomType, numberOfRooms);
        setBookingDates(dateCheckIn, dateCheckOut);
        setOccupancyDetails(adultRooms, childRooms);

        click(findElementByID(btnSearch));

        verifyHotelSelect(location, hotelName, roomType);

        click(findElementByID(radiobutton_0));
        click(findElementByID(btnContinue));

        verifyBookingPage(hotel_name_dis_val,location, hotelName, roomType);
        //take page screenshot

        bookHotelCriteria(firstName, lastName, billingAddress, ccNo, ccType, ccMonth, ccYear, ccCvvNo);

        click(findElementByID(btnBook_now));

        verifyBookingPage(order_no_val,location, hotelName, firstName);
        //take page screenshot

        click(findElementByID(btnMyItinerary));

        waitForElementBy("name",btnCancelSelected, 15);

        //take page screenshot
        captureScreenshot(driver,"To Cancel Selected");


        click(findElementByID(btnLogout));

        verifyTextPresent(logoutSuccess, false);

        captureScreenshot(driver,"Logout Success");




        //take page screenshot

         closeWindow();

    }




}