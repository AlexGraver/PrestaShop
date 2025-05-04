package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutAddressesPage extends BasePage {

    public CheckoutAddressesPage(WebDriver driver){
        super(driver);
    }

    public static final By FIRST_NAME = By.xpath("//input[@id=\"field-firstname\"]");
    public static final By LAST_NAME = By.xpath("//input[@id=\"field-lastname\"]");
    public static final By ADDRESS = By.xpath("//input[@id=\"field-address1\"]");
    public static final By CITY = By.xpath("//input[@id=\"field-city\"]");
    public static final By STATE = By.xpath("//select[@id=\"field-id_state\"]");
    public static final By COUNTRY = By.xpath("//select[@id=\"field-id_country\"]");
    public static final By ZIP_CODE = By.xpath("//input[@id=\"field-postcode\"]");
    public static final By PHONE = By.xpath("//input[@id=\"field-phone\"]");
    public static final By CONTINUE_BUTTON = By.xpath("//button[@name=\"confirm-addresses\"]");

    public CheckoutAddressesPage setFirstName(String firstName){
        setTextIntoElement(FIRST_NAME, firstName);
        return this;
    }

    public CheckoutAddressesPage setLastName(String lastName) {
        setTextIntoElement(LAST_NAME, lastName);
        return this;
    }

    public CheckoutAddressesPage setAddress(String address) {
        setTextIntoElement(ADDRESS, address);
        return this;
    }

    public CheckoutAddressesPage setCity(String city) {
        setTextIntoElement(CITY, city);
        return this;
    }

    public CheckoutAddressesPage selectState(String state) {
        selectFromDropdown(STATE, state);
        return this;
    }

    public CheckoutAddressesPage selectCountry(String country) {
        selectFromDropdown(COUNTRY, country);
        return this;
    }

    public CheckoutAddressesPage setZipCode(String zipCode) {
        setTextIntoElement(ZIP_CODE, zipCode);
        return this;
    }

    public CheckoutAddressesPage setPhone(String phone) {
        setTextIntoElement(PHONE, phone);
        return this;
    }

    public ShippingMethodPage clickContinueButton() {
        waitUntilElementClickable(CONTINUE_BUTTON).click();
        return new ShippingMethodPage(driver);
    }

}
