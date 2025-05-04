package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeAccessoryPage extends BasePage {

    public HomeAccessoryPage(WebDriver driver){
        super(driver);
    }

    private static final By IFRAME_BODY = By.xpath("//iframe[@id=\"framelive\"]");
    private static final By PRICE_SLIDER = By.xpath("//*[@class=\"ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all\"]");
    private static final By LEFT_SLIDER_HANDLE = By.xpath("(//a[@class=\"ui-slider-handle ui-state-default ui-corner-all\"])[1]");
    private static final By RIGHT_SLIDER_HANDLE = By.xpath("(//a[@class=\"ui-slider-handle ui-state-default ui-corner-all\"])[2]");

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
    }

    private int getElementActualOffset(By locator){
        WebElement element = findElement(locator);
        String offset = element.getDomProperty("offsetLeft");
        return Integer.valueOf(offset);
    }


}
