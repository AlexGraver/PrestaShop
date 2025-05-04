package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver){
        super(driver);
    }

    public static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//div[@class=\"text-sm-center\"]/a");

    public CheckoutAddressesPage checkout(){
        waitUntilElementClickable(PROCEED_TO_CHECKOUT_BUTTON).click();
        return new CheckoutAddressesPage(driver);
    }
}
