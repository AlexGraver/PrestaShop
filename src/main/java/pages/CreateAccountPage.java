package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends BasePage {

    public CreateAccountPage(WebDriver driver){
        super(driver);
    }

    public static final By MALE = By.xpath("//label[@for=\"field-id_gender-1\"]");
    public static final By FEMALE = By.xpath("//label[@for=\"field-id_gender-2\"]");
    public static final By FIRST_NAME = By.xpath("//input[@id=\"field-firstname\"]");
    public static final By LAST_NAME = By.xpath("//input[@id=\"field-lastname\"]");
    public static final By EMAIL = By.xpath("//input[@id=\"field-email\"]");
    public static final By PASSWORD = By.xpath("//input[@id=\"field-password\"]");
    public static final By BIRTHDATE = By.xpath("//input[@id=\"field-birthday\"]");
    public static final By RECEIVE_OFFERS_FROM_PARTNERS = By.xpath("//input[@name=\"optin\"]/..");
    public static final By AGREE_TERMS_CONDITIONS = By.xpath("//input[@name=\"psgdpr\"]/..");
    public static final By SIGN_UP_FOR_NEWSLETTER = By.xpath("//input[@name=\"newsletter\"]/..");
    public static final By CUSTOMER_DATA_POLICY = By.xpath("//input[@name=\"customer_privacy\"]/..");
    public static final By SAVE_FORM = By.xpath("//button[@data-link-action=\"save-customer\"]");

    public CreateAccountPage selectGender(By gender){
        waitUntilElementClickable(gender).click();
        return this;
    }

    public CreateAccountPage setFirstName(String name){
        setTextIntoElement(FIRST_NAME, name);
        return this;
    }

    public CreateAccountPage setLastName(String lastName){
        setTextIntoElement(LAST_NAME, lastName);
        return this;
    }

    public CreateAccountPage setEmail(String email){
        setTextIntoElement(EMAIL, email);
        return this;
    }

    public CreateAccountPage setPassword(String password){
        setTextIntoElement(PASSWORD, password);
        return this;
    }

    public CreateAccountPage setBirthday(String birthdate){
        setTextIntoElement(BIRTHDATE, birthdate);
        return this;
    }

    public CreateAccountPage agreeReceivePartnerOffers(){
        waitUntilElementClickable(RECEIVE_OFFERS_FROM_PARTNERS).click();
        return this;
    }

    public CreateAccountPage agreeTermsAndConditions(){
        waitUntilElementClickable(AGREE_TERMS_CONDITIONS).click();
        return this;
    }

    public CreateAccountPage agreeSignUpNewsletter(){
        waitUntilElementClickable(SIGN_UP_FOR_NEWSLETTER).click();
        return this;
    }

    public CreateAccountPage agreeCustomerDataPolicy(){
        waitUntilElementClickable(CUSTOMER_DATA_POLICY).click();
        return this;
    }

    public HomePage saveAccount(){
        waitUntilElementClickable(SAVE_FORM).click();
        log.info("Account created");
        return new HomePage(driver);
    }

}
