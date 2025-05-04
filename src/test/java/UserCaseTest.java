import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.CreateAccountPage;

public class UserCaseTest extends BaseTest {

    @Test
    void e2eTest(){
        initUiTest()
                .openLoginPage()
                .openCreateAccount()
                .selectGender(CreateAccountPage.MALE)
                .setFirstName("Alex")
                .setLastName("Test")
                .setEmail("email@email.lv")
                .setPassword("TestPasswordStrongVery123")
                .setBirthday("02/20/2000")
                .agreeReceivePartnerOffers()
                .agreeTermsAndConditions()
                .agreeSignUpNewsletter()
                .agreeCustomerDataPolicy()
                .saveAccount();
    }
}
