package baseTest;

import core.BasePage;
import core.driver.TestDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import pages.HomePage;


public class BaseTest {

    private static WebDriver driver;
    private static BasePage basePage;

    public HomePage initUiTest(){
        WebDriver driver = TestDriver.getDriver();
        BasePage basePage = new BasePage(driver);
        basePage.openUrl("http://demo.prestashop.com");
        return new HomePage(driver);
    }

//    @AfterMethod
//    public void tearDown(){
//        TestDriver.quitDriver();
//    }

}

