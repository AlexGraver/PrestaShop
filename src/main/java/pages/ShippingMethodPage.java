package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingMethodPage extends BasePage {

    public ShippingMethodPage(WebDriver driver){
        super(driver);
    }

    private static final By CONTINUE_BUTTON = By.xpath("//button[@name=\"confirmDeliveryOption\"]");

    public PaymentPage pressContinue(){
        waitUntilElementClickable(CONTINUE_BUTTON).click();
        return new PaymentPage(driver);
    }
}
