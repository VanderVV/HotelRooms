package pages;

import static base.BasePage.*;
import static pages.HotelObjects.*;


public class BookHotelPage {

    public static void bookHotelCriteria(String firstName, String lastName, String billingAddress, String ccNo, String ccType, String ccMonth, String ccYear, String ccCvvNo) {

        setText(findElementByID(first_name_input), firstName);
        setText(findElementByID(last_name_input), lastName);
        setText(findElementByID(billing_address_input), billingAddress);
        setText(findElementByID(credit_card_no_input), ccNo);

        String yearValue = "";
        yearValue = getAttributeValueByText(findElementByID(cc_exp_year_dd),ccYear);

        selectOptionByValue(findElementByID(cc_type_dd), ccType);
        selectOptionByValue(findElementByID(cc_exp_month_dd), getAttributeValueByText(findElementByID(cc_exp_month_dd),ccMonth));
        selectOptionByText(findElementByID(cc_exp_year_dd), yearValue);

        setText(findElementByID(cc_cvv_no_input), ccCvvNo);

    }




}
