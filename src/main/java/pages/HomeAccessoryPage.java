package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeAccessoryPage extends BasePage {

    public HomeAccessoryPage(WebDriver driver){
        super(driver);
        log.info("HomeAccessoryPage opened");
    }

    private static final By LEFT_SLIDER_HANDLE = By.xpath("(//a[@class=\"ui-slider-handle ui-state-default ui-corner-all\"])[1]");
    private static final By RIGHT_SLIDER_HANDLE = By.xpath("(//a[@class=\"ui-slider-handle ui-state-default ui-corner-all\"])[2]");
    private static final By PRODUCT_ITEM = By.xpath("//div[@class=\"js-product product col-xs-12 col-sm-6 col-xl-4\"]");
    private static final By IFRAME_BODY = By.xpath("//iframe[@id=\"framelive\"]");

    public void setPriceFilter(int min, int max){
        sleep(2000);

        int minDefault = 14;
        int maxDefault = 42;
        int sliderRange = maxDefault - minDefault;

        int offsetMin = getElementActualOffset(LEFT_SLIDER_HANDLE);
        int offsetMax = getElementActualOffset(RIGHT_SLIDER_HANDLE);

        double stepOffset = (double) (offsetMax-offsetMin)/sliderRange;

        int leftOffset = (int)((min - minDefault)*(stepOffset));
        int rightOffset = (int)((maxDefault - max)*(stepOffset));

        moveElementWithMouse(findElement(LEFT_SLIDER_HANDLE), leftOffset, 0);
        moveElementWithMouse(findElement(RIGHT_SLIDER_HANDLE), -rightOffset, 0);

        sleep(2000);
    }

    private int getElementActualOffset(By locator){
        WebElement element = findElement(locator);
        String offset = element.getDomProperty("offsetLeft");
        return Integer.valueOf(offset);
    }

    public List<WebElement> getFilteredItems(){
        return findElements(PRODUCT_ITEM);
    }

    public double getItemPrice(WebElement element){
        double priceInt = 0.0;
        String text = element.getText();
        Pattern pattern = Pattern.compile("€(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String price = matcher.group(1);
            priceInt = Double.valueOf(price);
        }
        return priceInt;
    }

    public ItemPage openRandomItemPage(){
        getFilteredItems().stream().findAny().get().click();
        return new ItemPage(driver);
    }

    public void waitForBody(){
        try{
            waitIframeIsAvailableAndSwitchToIt(IFRAME_BODY);
        }catch (NoSuchElementException e){
            driver.switchTo().defaultContent();
            waitIframeIsAvailableAndSwitchToIt(IFRAME_BODY);
            log.info("Page was opened twice, BODY iFrame reloaded");
        }
    }

}
