package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductSuccessAddPage extends BasePage {

    public ProductSuccessAddPage(WebDriver driver){
        super(driver);
        log.info("Product success add window is opened");
    }

    private static final By SUBTOTAL_PRICE = By.xpath("//span[@class=\"subtotal value\"]");
    private static final By CONTINUE_SHOPPING = By.xpath("//div[@class=\"cart-content-btn\"]/button");
    private static final By PROCEED_TO_CHECKOUT = By.xpath("//div[@class=\"cart-content-btn\"]/a");

    public void continueShopping(){
        waitUntilElementClickable(CONTINUE_SHOPPING).click();
    }

    public double getSubTotalPrice(){
        String price = waitUntilElementDisplayed(SUBTOTAL_PRICE)
                .getDomProperty("innerText")
                .replaceAll("[^\\d.,]", "")
                .trim();
        return Double.valueOf(price);
    }

    public CartPage proceedToCheckOut(){
        waitUntilElementClickable(PROCEED_TO_CHECKOUT).click();
        return new CartPage(driver);
    }
}
