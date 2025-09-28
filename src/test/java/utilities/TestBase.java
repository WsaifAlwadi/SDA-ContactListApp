package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        baseUrl = ConfigReader.getProperty("urlContactList");
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}