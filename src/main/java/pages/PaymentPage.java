package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver){
        super(driver);
    }

    public static final By AGREE_TO_TERMS = By.xpath("//input[@type=\"checkbox\"]");
    public static final By PLACE_ORDER_BUTTON = By.xpath("//div[@class=\"ps-shown-by-js\"]/button");

    public PaymentPage agreeToTerms(){
        waitUntilElementClickable(AGREE_TO_TERMS).click();
        return this;
    }

    public void placeOrder(){
        waitUntilElementClickable(PLACE_ORDER_BUTTON).click();
    }
}
