package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import static base.BasePage.*;
import static pages.HotelObjects.*;
import static utils.ConfigReader.get;

public class LoginFlow {

    public static void loginWithValidUser() {

        setText(findElementByID(username_input), get("username"));
        setText(findElementByID(password_input), get("password"));

        click(findElementByID(btnLogin));

    }
}