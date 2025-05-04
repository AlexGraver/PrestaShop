package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver){
        super(driver);
    }

    public static final By AGREE_TO_TERMS = By.xpath("//input[@type=\"checkbox\"]");

    public void agreeToTerms(){
        waitUntilElementClickable(AGREE_TO_TERMS).click();
    }
}
