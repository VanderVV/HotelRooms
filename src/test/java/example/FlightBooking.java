package example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightBooking {

    WebDriver driver;
    WebDriverWait wait;

    public void login() {}

    public void searchFlight() {}

    public void selectFlight() {}

    public void enterPassengerDetails() {}

    public void payment() {}

    public void verifyBooking() {}

    public void closeBrowser() {}

    public static void main(String[] args) {

        FlightBooking flow = new FlightBooking();

        flow.login();
        flow.searchFlight();
        flow.selectFlight();
        flow.enterPassengerDetails();
        flow.payment();
        flow.verifyBooking();
        flow.closeBrowser();
    }
}