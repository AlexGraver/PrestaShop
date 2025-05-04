package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccessoryPage extends BasePage {

    public AccessoryPage(WebDriver driver){
        super(driver);
    }

    public static final By ACCESSORY_SUB_MENU = By.xpath("//ul[@class=\"category-sub-menu\"]");
    public static final By HOME_ACCESSORIES = By.xpath(".//a[text() = \"Home Accessories\"]");


    public HomeAccessoryPage openHomeAccessoryPage(){
        findElement(ACCESSORY_SUB_MENU).findElement(HOME_ACCESSORIES).click();
        return new HomeAccessoryPage(driver);
    }

}
