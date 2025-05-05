package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends BasePage {

    public ItemPage(WebDriver driver){
        super(driver);
    }

    public static final By QUANTITY = By.xpath("//input[@aria-label=\"Quantity\"]");
    public static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-button-action=\"add-to-cart\"]");
    public static final By ITEM_PRICE = By.xpath("//span[@class=\"current-price-value\"]");

    public void setQuantity(int quantity){
        setTextIntoElement(QUANTITY, String.valueOf(quantity));
    }

    public ProductSuccessAddPage addToCart(){
        waitUntilElementClickable(ADD_TO_CART_BUTTON).click();
        log.info("Product added to cart");
        return new ProductSuccessAddPage(driver);
    }

    public double getItemPrice(){
        String price = findElement(ITEM_PRICE)
                .getDomProperty("innerText")
                .replaceAll("[^\\d.,]", "")
                .trim();
        return Double.valueOf(price);
    }

}
