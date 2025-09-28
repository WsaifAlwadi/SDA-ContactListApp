package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddContactPage {
    WebDriver driver;

    public AddContactPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("email");
    By phone = By.id("phone");
    By street1 = By.id("street1");
    By city = By.id("city");
    By stateProvince = By.id("stateProvince");
    By postalCode = By.id("postalCode");
    By country = By.id("country");
    By submit = By.id("submit");

    public void addContact(String fn, String ln, String em, String ph,
                           String st, String ct, String stt, String pc, String cntry) {
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(email).sendKeys(em);
        driver.findElement(phone).sendKeys(ph);
        driver.findElement(street1).sendKeys(st);
        driver.findElement(city).sendKeys(ct);
        driver.findElement(stateProvince).sendKeys(stt);
        driver.findElement(postalCode).sendKeys(pc);
        driver.findElement(country).sendKeys(cntry);
        driver.findElement(submit).click();
    }
}