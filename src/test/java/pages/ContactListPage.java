package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.List;

public class ContactListPage {
    public ContactListPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "add-contact")
    public WebElement addContactBtn;

    @FindBy(css = "table tbody tr")
    public List<WebElement> contactsRows;
}