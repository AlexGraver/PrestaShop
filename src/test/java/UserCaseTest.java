import baseTest.BaseTest;
import core.helpers.RandomUserData;
import org.testng.annotations.Test;
import pages.CreateAccountPage;

public class UserCaseTest extends BaseTest {

    @Test
    void e2eTest(){

        RandomUserData data = new RandomUserData();

        initUiTest()
                .openLoginPage()
                .openCreateAccount()
                .selectGender(CreateAccountPage.MALE)
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setEmail(data.getEmail())
                .setPassword("TestPasswordStrongVery123")
                .setBirthday("02/20/2000")
                .agreeReceivePartnerOffers()
                .agreeTermsAndConditions()
                .agreeSignUpNewsletter()
                .agreeCustomerDataPolicy()
                .saveAccount();
    }
}
