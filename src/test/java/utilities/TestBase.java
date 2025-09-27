package utilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {

    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("urlContactList"));
    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
        //
    }
}