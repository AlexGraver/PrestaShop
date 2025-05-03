import baseTest.BaseTest;
import org.testng.annotations.Test;

public class UserCaseTest extends BaseTest {

    @Test
    void e2eTest(){
        initUiTest()
                .openLoginPage()
                .openCreateAccount();
    }
}
