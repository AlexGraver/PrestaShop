package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    private static final By CREATE_ACCOUNT = By.xpath("//div[@class=\"no-account\"]/a");

    public CreateAccountPage openCreateAccount(){
        waitUntilElementClickable(CREATE_ACCOUNT).click();
        return new CreateAccountPage(driver);
    }

}
