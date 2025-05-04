import baseTest.BaseTest;
import core.helpers.RandomUserData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomeAccessoryPage;
import pages.HomePage;

import java.util.List;

public class UserCaseTest extends BaseTest {

    RandomUserData data;
    String firstname;
    String lastname;
    String email;
    String password;
    String birthday;

    @BeforeMethod
    void setUp(){
        data = new RandomUserData();
        firstname = data.getFirstName();
        lastname = data.getLastName();
        email = data.getEmail();
        password = "TestPasswordStrongVery123";
        birthday = "02/20/2000";
    }

    @Test
    void e2eTest(){
        CreateAccountPage createAccountPage = initUiTest()
                .openLoginPage()
                .openCreateAccount();
        HomePage homePage = createAccount(createAccountPage);
        checkUserLogin(homePage);
        HomeAccessoryPage homeAccessoryPage = openHomeAccessory(homePage);
        homeAccessoryPage.setPriceFilter(18, 23);
        checkItemPricesIsCorrectlyFiltered(homeAccessoryPage);
    }

    @Step
    private HomePage createAccount(CreateAccountPage createAccountPage){
        return createAccountPage.selectGender(CreateAccountPage.MALE)
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email)
                .setPassword(password)
                .setBirthday(birthday)
                .agreeReceivePartnerOffers()
                .agreeTermsAndConditions()
                .agreeSignUpNewsletter()
                .agreeCustomerDataPolicy()
                .saveAccount();
    }

    @Step
    void checkUserLogin(HomePage homePage){
        String actualName = homePage.getLoggedUserFullName();
        String expectedName = firstname + " " + lastname;
        Assert.assertEquals(actualName, expectedName);
    }

    @Step
    private HomeAccessoryPage openHomeAccessory(HomePage homePage){
        return homePage.openAccessoryPage().openHomeAccessoryPage();
    }

    @Step
    private void checkItemPricesIsCorrectlyFiltered(HomeAccessoryPage homeAccessoryPage){
        List<WebElement> items = homeAccessoryPage.getFilteredItems();
        for(WebElement item: items){
            double price = homeAccessoryPage.getItemPrice(item);
            Assert.assertTrue(price<=23 && price >= 18);
        }
    }

}
