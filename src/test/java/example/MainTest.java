package example;

import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.*;
import pages.LoginFlow;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.ReportManager;
import utils.ScreenshotUtil;

import static base.BasePage.driver;
import static base.BasePage.driverSetup;
import static example.MainHotelProgram.mainProgram;
import static pages.LoginFlow.loginWithValidUser;

public class MainTest {


    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public static void startReport() {
        extent = ExtentManager.getInstance();
    }


    @Before
    public void startTest() {
        test = extent.createTest("Hotel Booking Test");
        ReportManager.setTest(test);

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void HotelRoomsConfirmation() throws Exception {

        test.info("Opening application");

        test = extent.createTest("Hotel Booking Test");

        try {

            String location = "Sydney";
            String hotelName = "Hotel Sunshine";
            String roomType = "Standard";
            String numberOfRooms = "2 - Two";
            String dateCheckIn = "25/05/2024";
            String dateCheckOut = "27/05/2024";
            String adultRooms = "1 - One";
            String childRooms = "1 - One";

            String firstName = "Waal";
            String lastName = "Automation";
            String billingAddress = "1 Frank Dr, Pojo, KimWaal 2020";
            String ccNo = "4117011465111001";
            String ccType = "VISA";
            String ccMonth = "May";
            String ccYear = "2029";
            String ccCvvNo = "475";

            mainProgram(location, hotelName, roomType, numberOfRooms, dateCheckIn, dateCheckOut, adultRooms, childRooms, firstName, lastName, billingAddress, ccNo, ccType, ccMonth, ccYear, ccCvvNo);


            test.pass("Booking completed");

        } catch (Exception e) {

            String path = ScreenshotUtil.captureScreenshot(driver,"failure");

            test.fail("Test Failed")
                    .addScreenCaptureFromPath(path);

            throw e;
        }



    }

    @Test
    public void HotelRoomsConfirmationXX() {

        driverSetup(ConfigReader.get("url"));

        loginWithValidUser();

//        HotelSearchFlow hotelSearch = new HotelSearchFlow(driver);
//
//        hotelSearch.searchHotel(
//                "Sydney",
//                "Hotel Sunshine",
//                "Standard"
//        );
    }

    @Test
    public void mainTest() {
    }

    @AfterClass
    public static void endReport() {
        extent.flush();
    }
}