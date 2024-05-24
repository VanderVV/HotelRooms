package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.Main.getDriver;

public class BasePage {
    public static WebDriver _driver =  getDriver();
    static final String messageNoSuchElementException = "Unable to locate element by name: ";
    static final String messageElementNotVisibleException = "Element is Hidden and can't be found: ";
    static final String messageElementNotSelectableException = "WebDriver found that this element was not selectable: ";
    static final String messageAssertionError = "Unable to verify";
    static final String messageTimeoutException = "Timeout when waiting for element: ";
    static final String messageElementNotInteractableException = "The following element is not interactable: ";
    static final int explicitWait = 5;
    String curPage;

    public void updateCurPage(String page) {
        curPage = page;
    }

    public static void setDriver(WebDriver driver) {
        _driver = driver;
    }

    public static void openBrowser(String url) {
        try {
            _driver.getPageSource();
        } catch (Exception e) {
        }
        _driver.navigate().to(url);
    }

    public static void navigateToUrl(String url) {
        _driver.navigate().to(url);
    }

    public static void maximizeWindow() {
        _driver.manage().window().maximize();

    }

    public static void setText(WebElement element, String text) {
        try {
            element.clear();

            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + element.toString());
        }
    }

    public static void waitForElement(String fieldName, int delay) {
        try {
            WebDriverWait wait = new WebDriverWait(_driver, delay);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldName)));
        } catch (TimeoutException e) {
            throw new TimeoutException(messageTimeoutException + fieldName);
        }
    }


    public static void sendKeys(String fieldName, String text) {
        try {
            BasePage.waitForElement(fieldName, explicitWait);

            WebElement element = _driver.findElement(By.id(fieldName));

            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + fieldName);
        } catch (ElementNotVisibleException e) {
            throw new ElementNotVisibleException(messageElementNotVisibleException + fieldName);
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
        } catch (ElementNotSelectableException e) {
            throw new ElementNotSelectableException(messageElementNotSelectableException + element.toString());
        }
    }
    public static void closeWindow() {
        _driver.close();
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
        ((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);

        BasePage.delay(delay);
    }

    public static void scrollToPosition(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;

        js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")");
    }

    public static boolean verifyTextPresent(String text, boolean isRegex) {
        boolean textPresent = false;
        if (isRegex) {
            if (_driver.getPageSource().matches(text)) {
                textPresent = true;
                System.out.println("text Present");
            }
        } else {
            if (_driver.getPageSource().contains(text)) {
                textPresent = true;
                System.out.println("text Present");
            } else {
                System.out.println("text Not Present");
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
            WebElement element = _driver.findElement(By.name(fieldName));

            elementPresent = element.isDisplayed();

        } catch (AssertionError e) {
            throw new AssertionError(messageAssertionError);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(messageNoSuchElementException + fieldName);
        }
        return elementPresent;
    }
}


