package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ContactListPage;
import pages.AddContactPage;
import utilities.ConfigReader;
import utilities.TestBase;

public class ContactListTest extends TestBase {

    @Test
    public void testContactListFlow() {
        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);
        AddContactPage addContactPage = new AddContactPage(driver);

        String firstName = ConfigReader.getProperty("cl_fn");
        String lastName = ConfigReader.getProperty("cl_ln");
        String email = ConfigReader.getProperty("cl_email");
        String password = ConfigReader.getProperty("cl_password");

        // 1. Register
        loginPage.registerUser(firstName, lastName, email, password);
        Assert.assertTrue(driver.getPageSource().contains("Contact List"), "Registration failed!");

        // 2. Login
        loginPage.login(email, password);
        Assert.assertTrue(driver.getPageSource().contains("Contact List"), "Login failed!");

        // 3. Add 5 contacts
        for (int i = 1; i <= 5; i++) {
            contactListPage.clickAddContact();
            addContactPage.addContact(
                    firstName + i, lastName + i,
                    "contact" + i + System.currentTimeMillis() + "@mail.com",
                    "12345678" + i, "Street " + i,
                    "City" + i, "State" + i,
                    "0000" + i, "Country" + i
            );

            Assert.assertTrue(contactListPage.isContactDisplayed(firstName + i, lastName + i),
                    "Contact " + i + " not found!");
        }

        // 4. Verify count
        Assert.assertEquals(contactListPage.getContactCount(), 5, "Contact count mismatch!");
    }
}