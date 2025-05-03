package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
        waitIframeIsAvailableAndSwitchToIt(IFRAME_BODY);
    }

    private static final By IFRAME_BODY = By.xpath("//iframe[@id=\"framelive\"]");
    private static final By SIGN_IN = By.xpath("//div[@class=\"user-info\"]/a/i[@class=\"material-icons\"]");

    public LoginPage openLoginPage(){
        waitUntilElementClickable(SIGN_IN).click();
        return new LoginPage(driver);
    }
}
