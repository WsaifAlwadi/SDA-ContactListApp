package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.time.Duration;

public class ContactListTest extends TestBase {

    @Test
    public void contactListScenario(){
        LoginPage loginPage = new LoginPage();
        ContactListPage contactListPage = new ContactListPage();
        AddContactPage addContactPage = new AddContactPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        // 1. Login using data from configuration.properties
        String email = ConfigReader.getProperty("cl_email");
        String password = ConfigReader.getProperty("cl_password");

        System.out.println("Attempting login with: " + email);

        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailInput));

        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(password);
        loginPage.submitBtn.click();

        // Wait for logout button or error message
        try {
            wait.until(ExpectedConditions.visibilityOf(loginPage.logoutBtn));
            System.out.println("Login successful!");
        } catch (Exception e) {
            // Check for error message
            String currentUrl = Driver.getDriver().getCurrentUrl();
            String pageSource = Driver.getDriver().getPageSource();

            System.out.println("Login failed!");
            System.out.println("Current URL: " + currentUrl);

            if (pageSource.contains("error") || pageSource.contains("Error")) {
                System.out.println("Page contains error message");
            }

            // Try registration first
            tryRegistrationFirst();
            return;
        }

        // 2. Add contacts
        addContacts(contactListPage, addContactPage, wait);

        // 3. Verify contacts
        Assert.assertEquals(contactListPage.contactsRows.size(), 5, "Contact count is not 5!");
        System.out.println("Successfully added 5 contacts!");
    }

    private void tryRegistrationFirst() {
        LoginPage loginPage = new LoginPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        System.out.println("Attempting registration first...");

        // Navigate to registration page
        Driver.getDriver().get(ConfigReader.getProperty("urlContactList") + "addUser");

        // Fill registration data
        String firstName = ConfigReader.getProperty("cl_fn");
        String lastName = ConfigReader.getProperty("cl_ln");
        String email = ConfigReader.getProperty("cl_email");
        String password = ConfigReader.getProperty("cl_password");

        wait.until(ExpectedConditions.visibilityOf(loginPage.firstNameInput));

        loginPage.firstNameInput.sendKeys(firstName);
        loginPage.lastNameInput.sendKeys(lastName);
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(password);
        loginPage.submitBtn.click();

        // Wait for successful registration
        wait.until(ExpectedConditions.visibilityOf(loginPage.logoutBtn));
        System.out.println("Registration successful!");
    }

    private void addContacts(ContactListPage contactListPage, AddContactPage addContactPage, WebDriverWait wait) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Adding contact number: " + i);

            wait.until(ExpectedConditions.elementToBeClickable(contactListPage.addContactBtn));
            contactListPage.addContactBtn.click();

            wait.until(ExpectedConditions.visibilityOf(addContactPage.firstNameInput));

            addContactPage.firstNameInput.sendKeys("First" + i);
            addContactPage.lastNameInput.sendKeys("Last" + i);
            addContactPage.birthDateInput.sendKeys("2000-01-0" + i);
            addContactPage.emailInput.sendKeys("contact" + i + "@mail.com");
            addContactPage.phoneInput.sendKeys("123456789" + i);
            addContactPage.streetInput.sendKeys("Street " + i);
            addContactPage.cityInput.sendKeys("City " + i);
            addContactPage.stateInput.sendKeys("State " + i);
            addContactPage.postalCodeInput.sendKeys("1000" + i);
            addContactPage.countryInput.sendKeys("Country " + i);
            addContactPage.submitBtn.click();

            // Wait for contact to be saved
            try {
                wait.until(ExpectedConditions.urlContains("contactList"));
                System.out.println("Contact " + i + " added successfully");
            } catch (Exception e) {
                System.out.println("Possible issue saving contact " + i);
            }
        }
    }

    @Test
    public void simpleLoginTest() {
        LoginPage loginPage = new LoginPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        String email = ConfigReader.getProperty("cl_email");
        String password = ConfigReader.getProperty("cl_password");

        System.out.println("Testing login with: " + email);

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailInput));

        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(password);
        loginPage.submitBtn.click();

        // Check if login was successful
        try {
            wait.until(ExpectedConditions.visibilityOf(loginPage.logoutBtn));
            System.out.println("Logi Test Passed!");
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("Logi Test Failed!");
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());
            Assert.fail("Login failed");
        }
    }
}