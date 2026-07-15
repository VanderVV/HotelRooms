package example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightBookingFlow {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        driver.get("https://your-flight-application.com");

        //-------------------------
        // LOGIN
        //-------------------------

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("testuser");

        driver.findElement(By.id("password"))
                .sendKeys("Password123");

        driver.findElement(By.id("loginButton"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));

        System.out.println("Login Successful");


        //-------------------------
        // SEARCH FLIGHT
        //-------------------------

        driver.findElement(By.id("fromCity"))
                .sendKeys("Johannesburg");

        driver.findElement(By.id("toCity"))
                .sendKeys("Cape Town");

        driver.findElement(By.id("departureDate"))
                .click();

        driver.findElement(By.xpath("//td[@data-date='2026-08-15']"))
                .click();

        driver.findElement(By.id("returnDate"))
                .click();

        driver.findElement(By.xpath("//td[@data-date='2026-08-20']"))
                .click();

        driver.findElement(By.id("passengers"))
                .clear();

        driver.findElement(By.id("passengers"))
                .sendKeys("2");

        driver.findElement(By.id("searchFlights"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flightResults")));

        System.out.println("Flights Found");


        //-------------------------
        // SELECT FLIGHT
        //-------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(text(),'Select')])[1]")))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passengerForm")));

        System.out.println("Flight Selected");


        //-------------------------
        // PASSENGER DETAILS
        //-------------------------

        driver.findElement(By.id("firstName"))
                .sendKeys("John");

        driver.findElement(By.id("lastName"))
                .sendKeys("Doe");

        driver.findElement(By.id("email"))
                .sendKeys("john@test.com");

        driver.findElement(By.id("phone"))
                .sendKeys("0712345678");

        driver.findElement(By.id("continueButton"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentPage")));

        System.out.println("Passenger Details Completed");


        //-------------------------
        // PAYMENT
        //-------------------------

        driver.findElement(By.id("cardNumber"))
                .sendKeys("4111111111111111");

        driver.findElement(By.id("cardHolder"))
                .sendKeys("John Doe");

        driver.findElement(By.id("expiry"))
                .sendKeys("12/30");

        driver.findElement(By.id("cvv"))
                .sendKeys("123");

        driver.findElement(By.id("payNow"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutPage")));

        System.out.println("Payment Completed");


        //-------------------------
        // VALIDATION
        //-------------------------

        WebElement confirmation =
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("bookingConfirmation")));

        if (confirmation.isDisplayed()) {
            System.out.println("Booking Successful");
        } else {
            System.out.println("Booking Failed");
        }

        //-------------------------
        // CLOSE
        //-------------------------

        driver.quit();
    }

}