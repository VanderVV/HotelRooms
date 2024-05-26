package stepDefinition;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static stepDefinition.HotelObjects.chromeWebdriver;
import static stepDefinition.HotelObjects.chromedriverPath;

public class BasePage {
    protected static WebDriver driver ;
    protected WebDriverWait wait ;

    public static void driverSetup(String url)
    {
        System.setProperty(chromeWebdriver, chromedriverPath);

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);
        WebDriver.Window window = driver.manage().window();
        window.maximize();
    }
    public BasePage(WebDriver driver)
    {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver,110);

        PageFactory.initElements(driver, this);
    }

    static final String messageNoSuchElementException = "Unable to locate element by name: ";
    static final String messageAssertionError = "Unable to verify";
    static final String messageTimeoutException = "Timeout when waiting for element: ";
    static final String messageElementNotInteractableException = "The following element is not interactable: ";

    public static void setText(WebElement element, String text) {
        try {
            element.clear();

            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        }
    }

    public static WebElement findElementBy(String finder, String value)
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
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldName)));
        } catch (TimeoutException e) {
            throw new TimeoutException(messageTimeoutException + fieldName);
        }
    }

    public static void click(WebElement element) {
        try {
            element.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
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

    public static void selectOptionByValue(WebElement element, String value) {
        try {
            Select dropdown = new Select(element);

            dropdown.selectByValue(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
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
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException(messageElementNotInteractableException + element.toString());
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

    public static boolean verifyTextPresent(String text, boolean isRegex) {
        boolean textPresent = false;
        if (isRegex) {
            if (driver.getPageSource().matches(text)) {
                textPresent = true;
                System.out.println("text Present");
            }
        } else {
            if (driver.getPageSource().contains(text)) {
                textPresent = true;
                System.out.println("text Present :  test Passed");
            } else {
                System.out.println("text Not Present : test Failed");
            }
        }
        try {
        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError + " text: " + text);
        }
        return textPresent;

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
}


