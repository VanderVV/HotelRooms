package base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BasePage {
    public static WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(110));

        PageFactory.initElements(driver, this);
    }

    private static By getLocator(String finder, String value) {

        finder = finder.toLowerCase();

        switch (finder) {
            case "id":
                return By.id(value);

            case "name":
                return By.name(value);

            case "classname":
                return By.className(value);

            case "xpath":
                return By.xpath(value);

            default:
                return By.xpath(value);
        }
    }


    public static void driverSetup(String url) {

        ChromeOptions options = new ChromeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(options);  // Selenium automatically finds the driver

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }


//    public static void driverSetup(String url) {
//        System.setProperty(chromeWebdriver, chromedriverPath);
//
//        ChromeOptions options = new ChromeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.NONE);
//        driver = new ChromeDriver(options);
//
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        driver.get(url);
//        WebDriver.Window window = driver.manage().window();
//        window.maximize();
//    }

    static final String messageNoSuchElementException = "Unable to locate element by name: ";
    static final String messageAssertionError = "Unable to verify";
    static final String messageTimeoutException = "Timeout when waiting for element: ";
    static final String messageElementNotInteractableException = "The following element is not interactable: ";

    public static void setText(WebElement element, String text) {
        try {
            element.clear();

            element.sendKeys(text);
            ReportManager.getTest().info("Entered text: " + text);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element);
        }
    }

    public static WebElement findElementBy(String finder, String value) {

        try {
            return driver.findElement(getLocator(finder, value));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement findElementByID(String value) {
        WebElement element;
        try {
            element = driver.findElement(By.id(value));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return element;

    }

    public static void waitForElementByID(String fieldName, int delay) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(delay));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldName)));

            ReportManager.getTest().info("Waiting for element ID: " + fieldName);
        } catch (TimeoutException e) {
            throw new TimeoutException(messageTimeoutException + fieldName);
        }
    }

    public static WebElement waitForElementBy(String finder, String value, int delay) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(delay));

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(getLocator(finder, value))
            );

            ReportManager.getTest()
                    .info("Waiting for element " + finder + ": " + value);

            return element;

        } catch (TimeoutException e) {

            throw new TimeoutException(messageTimeoutException + value);
        }
    }

    public static void click(WebElement element) {
        try {
            element.click();
            ReportManager.getTest().info("Clicked element: " + element);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element);
        }
    }

    public static void delay(int delay) {
        int delayInMillis = delay * 1000;
        try {
            Thread.sleep(delayInMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectOptionByValuevv(WebElement element, String value){
        try {
            Select dropdown = new Select(element);

            dropdown.selectByValue(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        } catch (Exception e) {
            String message = "Failed selecting dropdown value: " + value;

            ReportManager.getTest().fail(message);

            Assert.fail(message);
        }
    }

    public static String getAttributeValueByText(WebElement element, String text) {

        try {

            Select dropdown = new Select(element);

            for (WebElement option : dropdown.getOptions()) {
                if (option.getText().equals(text)) {

                    String value = option.getAttribute("value");

                    ReportManager.getTest()
                            .info("Retrieved value '" + value + "' for text '" + text + "'");

                    return value;
                }
            }

            throw new NoSuchElementException("No option found with text: " + text);

        } catch (NoSuchElementException e) {

            throw new NoSuchElementException(messageNoSuchElementException + element.toString());

        } catch (Exception e) {

            String message = "Failed retrieving dropdown value for text: " + text;

            ReportManager.getTest().fail(message);

            Assert.fail(message);

            return null;
        }
    }

    public static void selectOptionByValue(WebElement element, String value) {

        try {

            Select dropdown = new Select(element);
            dropdown.selectByValue(value);

            ReportManager.getTest()
                    .info("Selected value '" + value + "' from dropdown");

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());

        } catch (Exception e) {

            String message = "Failed selecting dropdown value: " + value;

            ReportManager.getTest().fail(message);

            Assert.fail(message);
        }
    }

    public static void selectOptionByText(WebElement element, String value)  {
        try {
            Select dropdown = new Select(element);

            dropdown.selectByVisibleText(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        } catch (Exception e) {
            String message = "Failed selecting dropdown value: " + value;

            ReportManager.getTest().fail(message);

            Assert.fail(message);
        }
    }
    public static void closeWindow() {
        driver.close();
    }

    public static String getAttributeValue(WebElement element, String attribute) {
        String attributeValue = "";
        try {
            attributeValue = element.getAttribute(attribute);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element);
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException(messageElementNotInteractableException + element);
        }

        return attributeValue;
    }

    public static String getAttributeValue(WebElement element) {
        String attributeValue = "";
        try {
            attributeValue = element.getAttribute("value");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element);
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException(messageElementNotInteractableException + element);
        }

        return attributeValue;
    }

    public static void scrollToElement(WebElement element, int delay) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);

        BasePage.delay(delay);
    }

    public static void scrollToPosition(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")");
    }

    /**
     * Verifies whether a specific text value exists within the current page source.
     *
     * The method supports two validation modes controlled by the {@code isRegex} parameter:
     *
     * 1. Regex Match (isRegex = true)
     *    - The provided text is treated as a regular expression pattern.
     *    - The method uses String.matches() to evaluate the entire page source against the regex.
     *    - This requires the pattern to match the full string according to regex rules.
     *    - Useful when validating structured or formatted values such as:
     *        - Dates (e.g., \\d{2}/\\d{2}/\\d{4})
     *        - Dynamic IDs
     *        - Variable numeric or formatted text
     *
     * 2. Partial Text Search (isRegex = false)
     *    - The method performs a simple substring search using String.contains().
     *    - It checks whether the specified text exists anywhere in the page source.
     *    - This is useful when only verifying the presence of a specific word,
     *      phrase, or UI label without requiring an exact or structured match.
     *
     * Example usage:
     * verifyTextPresent("25/05/2024", false);   // checks if this exact text exists anywhere
     * verifyTextPresent("\\d{2}/\\d{2}/\\d{4}", true); // validates a date format using regex
     *
     * @param text     The text or regex pattern to search for in the page source.
     * @param isRegex  Determines the matching strategy:
     *                 - true  -> treat the text parameter as a regex pattern and perform a regex match.
     *                 - false -> perform a simple substring search using contains().
     *
     * @return true if the text or regex pattern is found according to the selected matching strategy,
     *         otherwise false.
     */
    public static boolean verifyTextPresent(String text, boolean isRegex) {
        boolean textPresent = false;
        if (isRegex) {
            if (driver.getPageSource().matches(text)) {
                textPresent = true;
                System.out.println("text '"+text+"' Present");
            }
        } else {
            if (driver.getPageSource().contains(text)) {
                textPresent = true;
                System.out.println("text '"+text+"' Present :  test Continues");
            } else {
                System.out.println("text '"+text+"' Not Present : test Failed");
            }
        }
        try {
        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError + " text: " + text);
        }
        return textPresent;

    }

    /**
     * Validates that a specific text present in the page source matches a given regex format.
     *
     * This method first checks if the provided value exists in the page source.
     * If the value is found, it then validates whether the value matches the
     * provided regular expression pattern.
     *
     * Example:
     * validateTextFormat("25/05/2024", "^\\d{2}/\\d{2}/\\d{4}$");
     *
     * @param text   The exact text expected to be present on the page.
     * @param regex  The regex pattern used to validate the format of the text.
     *
     * @return true if the text exists and matches the regex format, otherwise false.
     */
    public static boolean validateTextFormat(String text, String regex) {

        boolean isValid = false;

        String pageSource = driver.getPageSource();

        if (pageSource.contains(text)) {

            if (text.matches(regex)) {
                isValid = true;
                System.out.println("Text found and format is valid: " + text);
            } else {
                System.out.println("Text found but format is invalid: "+ text);
            }

        } else {
            System.out.println("Text '"+text+"' is not present on page");
        }

        return isValid;
    }

    public static boolean verifyElementPresent(String fieldName) {
        boolean elementPresent = false;

        try {
            WebElement element = driver.findElement(By.name(fieldName));

            elementPresent = element.isDisplayed();

        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + fieldName);
        }
        return elementPresent;
    }


    /**
     * Verifies if specified element has a specified text.
     * @param element            Web element to verify
     * @param checkPointValue        The expected text of the attribute
     * @param test                The ExtendBase Object
     * @return True if Text is present on element
     */
    public static boolean verifyElementText(WebElement element, String checkPointValue,  ExtentTest test) {

        boolean result = false;

        ExtentTest tests = null;
        tests.log(Status.INFO, "Scenario: Validate");

        result =  verifyElementText(element, checkPointValue);

        test.log(Status.PASS, "Expected value: " + checkPointValue + "," + " is present on element: " +   element.toString() + "'s text");

        return result;
    }

    /***
     * Verifies if specified element has a specified text.
     * @param element        Web element to verify
     * @param checkPoint    The expected text of the attribute
     * @return True if Text is present on element
     */
    public static boolean verifyElementText(WebElement element, String checkPoint) {
        String elementText = "";
        try {
            elementText = getAttributeValue(element);


            assertEquals(elementText, checkPoint);
        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        }
        return elementText.equals(checkPoint);
    }

    /**
     * Verifies if the specified element contains a given text value.
     * This is useful for validating partial values such as currency symbols,
     * prefixes, suffixes, or dynamic values where the full text may change.
     *
     * @param element      Web element to verify
     * @param checkPoint   The partial text expected to be present in the element
     * @return True if the element text contains the specified checkpoint value
     */
    public static boolean verifyElementTextContains(WebElement element, String checkPoint) {

        String elementText = "";

        try {
            elementText = getAttributeValue(element);

            if (!elementText.contains(checkPoint)) {
                throw new AssertionError(messageAssertionError + " Expected partial text: " + checkPoint);
            }

        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        }

        return elementText.contains(checkPoint);
    }
}


