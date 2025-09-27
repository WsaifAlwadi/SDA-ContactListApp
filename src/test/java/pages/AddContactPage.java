package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AddContactPage {
    public AddContactPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "firstName")
    public WebElement firstNameInput;

    @FindBy(id = "lastName")
    public WebElement lastNameInput;

    @FindBy(id = "birthdate")
    public WebElement birthDateInput;

    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "phone")
    public WebElement phoneInput;

    @FindBy(id = "street1")
    public WebElement streetInput;

    @FindBy(id = "city")
    public WebElement cityInput;

    @FindBy(id = "stateProvince")
    public WebElement stateInput;

    @FindBy(id = "postalCode")
    public WebElement postalCodeInput;

    @FindBy(id = "country")
    public WebElement countryInput;

    @FindBy(id = "submit")
    public WebElement submitBtn;
}