package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
        waitForBody();
    }

    private static final By IFRAME_BODY = By.xpath("//iframe[@id=\"framelive\"]");
    private static final By SIGN_IN = By.xpath("//div[@class=\"user-info\"]/a/i[@class=\"material-icons\"]");
    private static final By LOGGED_USER_NAME = By.xpath("//a[@class=\"account\"]");
    public Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private void waitForBody(){
        try{
            waitIframeIsAvailableAndSwitchToIt(IFRAME_BODY);
        }catch (NoSuchElementException e){
            driver.switchTo().defaultContent();
            waitIframeIsAvailableAndSwitchToIt(IFRAME_BODY);
            log.info("Page was opened twice, BODY iFrame reloaded");
        }
    }

    public LoginPage openLoginPage(){
        waitUntilElementClickable(SIGN_IN).click();
        return new LoginPage(driver);
    }

    public String getLoggedUserFullName(){
        return findElement(LOGGED_USER_NAME).getText();
    }

    public AccessoryPage openAccessoryPage(){
        return new AccessoryPage(driver);
    }
}


