package core;

import core.helpers.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.util.List;

public class BasePage {

    public Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private WaitHelper waitHelper;
    private JavascriptExecutor jsExecutor;
    private Actions actions;
    private Select select;
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public void openUrl(String url){
        driver.get(url);
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void navigateBack(){
        driver.navigate().back();
        log.info("Browser navigate back");
    }

    protected void sleep(int milliseconds){
        waitHelper.sleep(milliseconds);
    }

    protected WebElement waitUntilElementDisplayed(By locator){
        return waitHelper.waitUntilElementDisplayed(locator);
    }

    protected WebElement waitUntilElementClickable(By locator){
        return waitHelper.waitUntilElementClickable(locator);
    }

    protected void jsClick(WebElement element){
        jsExecutor.executeScript("arguments[0].click()", element);
    }

    protected WebElement findElement(By locator){
        return waitHelper.waitUntilElementIsPresentInDOM(locator);
    }

    protected void moveMouseToElementAndClick(WebElement element){
        actions.moveToElement(element).click().release().build().perform();
    }

    protected void waitIframeIsAvailableAndSwitchToIt(By iFrame){
        waitHelper.waitIframeAvailable(iFrame);
    }

    protected void setTextIntoElement(By element, String text){
        WebElement field = findElement(element);
            field.clear();
            sleep(500);
            field.sendKeys(text);
    }

    protected void setDomAttributeValue(WebElement element, String attribute, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attribute, value);
    }

    protected void moveElementWithMouse(WebElement element, int horizontal, int vertical){
        actions.clickAndHold(element).moveByOffset(horizontal, vertical).release().perform();
    }

    protected void scrollToElement(WebElement element){
        actions.scrollToElement(element).perform();
    }

    protected List<WebElement> findElements(By by) {
        return waitHelper.findElements(by);
    }

    protected void selectFromDropdown(By locator, String option){
        select = new Select(findElement(locator));
        select.selectByVisibleText(option);
    }


}

