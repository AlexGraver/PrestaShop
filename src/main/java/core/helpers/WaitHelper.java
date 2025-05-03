package core.helpers;

import core.configs.Configs;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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


}

