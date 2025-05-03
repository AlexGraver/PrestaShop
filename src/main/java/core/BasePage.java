package core;

import core.helpers.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

public class BasePage {

    public Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private WaitHelper waitHelper;
    private JavascriptExecutor jsExecutor;
    private Actions actions;
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public static void openUrl(String url){
        driver.get(url);
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    protected void navigateBack(){
        driver.navigate().back();
    }

    protected void sleep(int milliseconds){
        waitHelper.sleep(milliseconds);
    }




}

