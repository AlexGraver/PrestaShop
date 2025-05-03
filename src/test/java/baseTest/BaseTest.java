package baseTest;

import core.driver.TestDriver;
import org.testng.annotations.AfterMethod;


public class BaseTest {



    @AfterMethod
    public void tearDown(){
        TestDriver.quitDriver();
    }

}

