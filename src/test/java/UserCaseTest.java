import baseTest.BaseTest;
import core.helpers.RandomUserData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UserCaseTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(UserCaseTest.class);
    RandomUserData data;
    String firstname;
    String lastname;
    String email;
    String password;
    String birthday;
    String address;
    String city;
    String phone;
    String state;
    String country;
    String zipCode;
    int filterMinPrice;
    int filterMaxPrice;
    double cumulativePrice;
    SoftAssert softAssert;

    @BeforeMethod
    void setUp(){
        data = new RandomUserData();
        softAssert = new SoftAssert();
        firstname = data.getFirstName();
        lastname = data.getLastName();
        email = data.getEmail();
        password = "TestPasswordStrongVery123";
        birthday = "02/20/2000";
        address = "Baker Street";
        city = "New York";
        phone = "92304881085";
        state = "New York";
        country = "United States";
        zipCode = "12345";
        filterMinPrice = 18;
        filterMaxPrice = 23;
        cumulativePrice = 0;

    }

    @Test
    @Description("E2E user flow test case: account creation -> product order")
    void e2eTest(){
        CreateAccountPage createAccountPage = initUiTest()
                .openLoginPage()
                .openCreateAccount();
        HomePage homePage = createAccount(createAccountPage);

        checkUserLogin(homePage);

        HomeAccessoryPage homeAccessoryPage = openHomeAccessory(homePage);
        homeAccessoryPage.setPriceFilter(filterMinPrice, filterMaxPrice);

        checkItemPricesIsCorrectlyFiltered(homeAccessoryPage);

        ItemPage itemPage = homeAccessoryPage.openRandomItemPage();
        ProductSuccessAddPage productSuccessAddPage = addItemToCart(itemPage, 2);

        checkSubTotalPrice(productSuccessAddPage);

        productSuccessAddPage.continueShopping();
        productSuccessAddPage.navigateBack();
        homeAccessoryPage.waitForBody();
        itemPage = homeAccessoryPage.openRandomItemPage();
        productSuccessAddPage = addItemToCart(itemPage, 1);

        checkSubTotalPrice(productSuccessAddPage);

        CartPage cartPage = productSuccessAddPage.proceedToCheckOut();
        CheckoutAddressesPage checkoutAddressesPage = cartPage.checkout();
        ShippingMethodPage shippingMethodPage = fillCheckoutAddressesPage(checkoutAddressesPage);
        PaymentPage paymentPage = selectShippingMethod(shippingMethodPage);
        confirmPayment(paymentPage);

        assertAll();


    }

    @Step("User account creation flow")
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

    @Step("Check user is created and logged in")
    void checkUserLogin(HomePage homePage){
        String actualName = homePage.getLoggedUserFullName();
        String expectedName = firstname + " " + lastname;
        softAssert.assertEquals(actualName, expectedName, "User Name incorrect");
        log.info("Login correct");
    }

    @Step("Open Home accessory page")
    private HomeAccessoryPage openHomeAccessory(HomePage homePage){
        return homePage.openAccessoryPage().openHomeAccessoryPage();
    }

    @Step("Check price filter is working correctly")
    private void checkItemPricesIsCorrectlyFiltered(HomeAccessoryPage homeAccessoryPage){
        List<WebElement> items = homeAccessoryPage.getFilteredItems();
        for(WebElement item: items){
            double price = homeAccessoryPage.getItemPrice(item);
            softAssert.assertTrue(price<=filterMaxPrice && price >= filterMinPrice);
        }
        log.info("All items prices are in selected filter range");
    }

    @Step("Add item to cart")
    private ProductSuccessAddPage addItemToCart(ItemPage itemPage, int quantity){
        cumulativePrice = cumulativePrice + (itemPage.getItemPrice())*quantity;
        if(quantity > 1){
            itemPage.setQuantity(quantity);
            log.info("Product quantity was changed");
        }
        log.info("Product quantity is default: 1");
        return itemPage.addToCart();
    }

    @Step("Check price in product success window correctly calculated")
    private void checkSubTotalPrice(ProductSuccessAddPage productSuccessAddPage){
        double actualPrice = productSuccessAddPage.getSubTotalPrice();
        double calculatedPrice = BigDecimal.valueOf(cumulativePrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
        softAssert.assertTrue(actualPrice == calculatedPrice,
                "SubTotalPrice incorrect: " + actualPrice + "expected, but got: " + calculatedPrice);
        log.info("SubTotal price is correct");
    }

    @Step("Fill Checkout address form")
    private ShippingMethodPage fillCheckoutAddressesPage(CheckoutAddressesPage checkoutAddressesPage){
        return checkoutAddressesPage.setFirstName(firstname)
                .setLastName(lastname)
                .setAddress(address)
                .setCity(city)
                .selectState(state)
                .setZipCode(zipCode)
                .selectCountry(country)
                .setPhone(phone)
                .clickContinueButton();
    }

    @Step("Soft assert of all checks during test case flow")
    private void assertAll(){
        softAssert.assertAll();
    }

    @Step("Select shipping method")
    private PaymentPage selectShippingMethod(ShippingMethodPage shippingMethodPage){
        return shippingMethodPage.pressContinue();
    }

    @Step("Place order")
    private void confirmPayment(PaymentPage paymentPage){
        paymentPage.agreeToTerms().placeOrder();
    }


}
