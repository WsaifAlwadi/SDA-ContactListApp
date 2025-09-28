package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactListPage{
    WebDriver driver;

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
    }

    By addContactBtn = By.id("d-contact");
    By contactCards = By.className("contactTableBodyRow");

    public void clickAddContact() {
        driver.findElement(addContactBtn).click();
    }

    public boolean isContactDisplayed(String fn, String ln) {
        List<WebElement> contacts = driver.findElements(contactCards);
        for (WebElement c : contacts) {
            if (c.getText().contains(fn) && c.getText().contains(ln)) {
                return true;
            }
        }
        return false;
    }

    public int getContactCount() {
        return driver.findElements(contactCards).size();
    }
}