package core.helpers;

import core.configs.Configs;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;
    private FluentWait<WebDriver> fluentWait;
    private Configs configs = ConfigFactory.create(Configs.class);


    public WaitHelper(WebDriver driver){
        this.driver = driver;
        initImplicitWait(configs.implicitWait());
        initWebDriverWait(configs.explicitWait());
        fluentWait = getFluentWait();
    }

    private void initImplicitWait(int millisec){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(millisec));
    }

    private void initWebDriverWait(int sec){
        wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    public FluentWait<WebDriver> getFluentWait(){
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(configs.fluentTimeout()))
                .pollingEvery(Duration.ofMillis(configs.fluentPolling()))
                .ignoring(TimeoutException.class)
                .ignoring(ElementClickInterceptedException.class);
    }

    public void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitUntilElementDisplayed(By locator){
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilElementClickable(By locator){
        return fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilElementIsPresentInDOM(By locator){
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitIframeAvailable(By iFrame){
        fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> list;
        try {
            list = fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (TimeoutException e) {
            list = new ArrayList<>();
        }
        return list;
    }


}

